import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import javax.swing.*;

import java.util.Vector;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;

public class ObjectChatServer extends JFrame {
  private int port;
  private ServerSocket serverSocket = null;
  private Thread acceptThread = null;
  private Vector<ClientHandler> users = new Vector<ClientHandler>();
  private DefaultStyledDocument document;
  private JTextPane t_display;
  private JButton b_connect, b_disconnect, b_exit;

  public ObjectChatServer(int port) {
    super("ObjectChat Server");

    buildGUI();

    setBounds(500,100,400,300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setVisible(true);

    this.port = port;
  }

  public void buildGUI() {
    add(createDisplayPanel(), BorderLayout.CENTER);
    add(createControlPanel(), BorderLayout.SOUTH);

  }

  public JPanel createDisplayPanel() {
    JPanel p = new JPanel(new BorderLayout());
    document = new DefaultStyledDocument();
    t_display= new JTextPane(document);

    JScrollPane scrollPane = new JScrollPane(t_display);

    t_display.setEditable(false);

    p.add(scrollPane, BorderLayout.CENTER);

    return p;
  }

  public JPanel createControlPanel() {
    JPanel p = new JPanel(new GridLayout());
    b_connect = new JButton("서버 시작");
    b_disconnect = new JButton("서버 종료");
    b_exit = new JButton("종료");

    p.add(b_connect);
    p.add(b_disconnect);
    p.add(b_exit);

    b_disconnect.setEnabled(false);

    b_connect.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        b_connect.setEnabled(false);
        b_disconnect.setEnabled(true);
        acceptThread = new Thread(new Runnable() {
          @Override
          public void run() {
            startServer();
          }
        });
        acceptThread.start();
      }
    });

    b_disconnect.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        b_connect.setEnabled(true);
        b_disconnect.setEnabled(false);
        disconnect();
      }
    });

    b_exit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        disconnect();
        System.exit(0);
      }
    });

    return p;
  }

  public String getLocalAddr() {
    String local_address = null;

    try {
      InetAddress local = InetAddress.getLocalHost();
      local_address = local.getHostAddress();
    } catch (UnknownHostException e) {
      System.out.println(e.getMessage());
      b_connect.setEnabled(true);
      b_disconnect.setEnabled(false);
    }

    return local_address;
  }

  public void startServer() {
    Socket clientSocket = null;

    try {
      serverSocket = new ServerSocket(port);
      printDisplay(">> 서버가 시작되었습니다: "+getLocalAddr());

      while(acceptThread == Thread.currentThread()) {
        clientSocket = serverSocket.accept();

        String cAddr = clientSocket.getInetAddress().getHostAddress();
        printDisplay(">> 클라이언트가 연결되었습니다: "+cAddr);

        ClientHandler cHandler = new ClientHandler(clientSocket);
        users.add(cHandler);
        cHandler.start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void disconnect() {
    try {
      if (serverSocket != null && !serverSocket.isClosed()) {
        serverSocket.close();
      }
      acceptThread = null;
      users.clear();
    } catch (IOException e) {
      printDisplay(e.getMessage());
    }
  }


  public void printDisplay(String str){
//    t_display.append(str+"\n");
    int len = t_display.getDocument().getLength();

    t_display.setCaretPosition(len);

    try {
      document.insertString(len,str+"\n",null);
    } catch (BadLocationException e) {
      throw new RuntimeException(e);
    }
  }

  public void printDisplay(ImageIcon image){
    int len = t_display.getDocument().getLength();

    t_display.setCaretPosition(len);

    if (image.getIconWidth() > 400) {
      Image img = image.getImage();
      Image changeImg = img.getScaledInstance(400, -1, Image.SCALE_SMOOTH);
      image = new ImageIcon(changeImg);
    }

    t_display.insertIcon(image);
    printDisplay("");
  }


  // 클라이언트 핸들러 클래스
  class ClientHandler extends Thread{
    private Socket clientSocket;
    private ObjectOutputStream out;
    private String uid;

    public ClientHandler(Socket clientSocket) {
      this.clientSocket = clientSocket;
    }

    void receiveMessage(Socket cs) {
      try {
        ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
        out = new ObjectOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));

        ChatMsg msg;
        while((msg = (ChatMsg) in.readObject())!=null) {
//          if(msg.contains("/uid:")) {
//            String[] tok = msg.split(":");
//            uid = tok[1];
//            printDisplay(">> 새 참가자: "+uid);
//            printDisplay(">> 현재 참가자 수: "+users.size());
//            continue;
//          }
          String message;
          switch (msg.mode) {
            case ChatMsg.MODE_LOGIN -> {
              uid = msg.userID;
              printDisplay(">> 새 참가자: " + uid);
              printDisplay(">> 현재 참가자 수: " + users.size());
            }
            case ChatMsg.MODE_LOGOUT -> {
              break;
            }
            case ChatMsg.MODE_TX_STRING, ChatMsg.MODE_TX_FILE -> {
              message = uid + ": " + msg.message;
              printDisplay(message);
              printDisplay("");
              broadcasting(msg);
            }
            case ChatMsg.MODE_TX_IMAGE -> {
              message = uid + ": " + msg.message;
              printDisplay(message);
              printDisplay(msg.image);
              broadcasting(msg);
            }
          }
        }

        users.removeElement(this);
        printDisplay(">> "+uid + " 퇴장. 현재 참가자 수: " + users.size());
      } catch (IOException e) {
        e.printStackTrace();
        users.removeElement(this);
        printDisplay(">> "+uid + " 연결 끊김. 현재 참가자 수: " + users.size());
      } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
      } finally {
        try {
          cs.close();
        } catch (IOException e) {
          System.err.println(">> 서버 닫기 오류: "+e.getMessage());
          System.exit(-1);
        }
      }
    }

    public void send(ChatMsg msg) {
      try {
        out.writeObject(msg);
        out.flush();
      } catch (IOException e) {
        System.err.println(">> 클라이언트 일반 전송 오류: "+e.getMessage());
      }
    }

    public void broadcasting(ChatMsg msg) {
      for(ClientHandler c : users) {
        c.send(msg);
      }
    }

    public void run() {
      receiveMessage(clientSocket);
    }

  }

  public static void main(String[] args) {
    new ObjectChatServer(54320);
  }

}
