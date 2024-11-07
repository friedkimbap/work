import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;

public class ObjTalk extends JFrame{
  private JTextField t_input, t_userID, t_hostAddr, t_portNum;
  private DefaultStyledDocument document;
  private JTextPane t_display;
  private JButton b_connect, b_disconnect, b_send, b_exit;
  private String serverAddress;
  private int serverPort;
  private Socket socket;
  private ObjectOutputStream out;
//  private BufferedReader in;
  private Thread receiveThread;
  private String uid;

  public ObjTalk(String serverAddress, int serverPort) {
    super("Object Talk");

    this.serverAddress = serverAddress;
    this.serverPort = serverPort;

    buildGUI();

    setBounds(100,100,500,300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setVisible(true);
  }

  public void buildGUI() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    add(createDisplayPanel(), BorderLayout.CENTER);

    JPanel p = new JPanel(new BorderLayout());
    p.add(createInputPanel(),BorderLayout.NORTH);
    p.add(createInfoPanel(), BorderLayout.CENTER);
    p.add(createControlPanel(), BorderLayout.SOUTH);

    add(p,BorderLayout.SOUTH);
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

  public JPanel createInputPanel() {
    JPanel p = new JPanel(new BorderLayout());
    t_input = new JTextField();
    b_send = new JButton("보내기");

    p.add(t_input, BorderLayout.CENTER);
    p.add(b_send, BorderLayout.EAST);

    b_send.setEnabled(false);
    t_input.setEnabled(false);

    ActionListener actionListener = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        sendMessage();
      }
    };

    b_send.addActionListener(actionListener);
    t_input.addActionListener(actionListener);

    return p;
  }

  public JPanel createInfoPanel() {
    JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));

    t_userID = new JTextField(7);
    t_hostAddr = new JTextField(12);
    t_portNum = new JTextField(5);

    t_userID.setText("guest" + getLocalAddr().split("\\.")[3]);
    t_hostAddr.setText(this.serverAddress);
    t_portNum.setText(String.valueOf(this.serverPort));

    t_portNum.setHorizontalAlignment(JTextField.CENTER);

    p.add(new JLabel("아이디:"));
    p.add(t_userID);
    p.add(new JLabel("서버주소:"));
    p.add(t_hostAddr);
    p.add(new JLabel("포트번호:"));
    p.add(t_portNum);

    return p;
  }

  public JPanel createControlPanel() {
    JPanel p = new JPanel(new GridLayout(1,3));
    b_connect = new JButton("접속하기");
    b_disconnect = new JButton("접속끊기");
    b_exit = new JButton("나가기");

    p.add(b_connect);
    p.add(b_disconnect);
    p.add(b_exit);

    b_disconnect.setEnabled(false);

    b_connect.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ObjTalk.this.serverAddress = t_hostAddr.getText(); // 이곳은 ActionListener객체임
        ObjTalk.this.serverPort = Integer.parseInt(t_portNum.getText());

        try {
          connectToServer();
          sendUserID();
        } catch (IOException ex) {
          printDisplay(">> 서버와의 연결 오류: "+ex.getMessage());
          return;
        }

        b_connect.setEnabled(false);
        b_disconnect.setEnabled(true);

        t_input.setEnabled(true);
        b_send.setEnabled(true);
        b_exit.setEnabled(false);

        t_userID.setEditable(false);
        t_hostAddr.setEditable(false);
        t_portNum.setEditable(false);
      }
    });

    b_disconnect.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        disconnect();

        b_connect.setEnabled(true);
        b_disconnect.setEnabled(false);

        t_input.setEnabled(false);
        b_send.setEnabled(false);
        b_exit.setEnabled(true);

        t_userID.setEditable(true);
        t_hostAddr.setEditable(true);
        t_portNum.setEditable(true);
      }

    });

    b_exit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }

    });

    return p;
  }

  public void printDisplay(String str){
//    t_display.append(str+"\n");
    int len = document.getLength();
    try {
      document.insertString(len,str+"\n",null);
    } catch (BadLocationException e) {
      throw new RuntimeException(e);
    }
    t_display.setCaretPosition(t_display.getDocument().getLength());
  }

  public void printDisplay(ImageIcon image){

  }

  public String getLocalAddr() {
    InetAddress local = null;
    String addr = "";

    try {
      local = InetAddress.getLocalHost();
      addr = local.getHostAddress();
      System.out.println(addr);
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }

    return addr;
  }

  public void connectToServer() throws IOException, UnknownHostException {
    socket = new Socket();
    SocketAddress sa = new InetSocketAddress(serverAddress, serverPort);
    socket.connect(sa, 3000);

    out = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));

    receiveThread = new Thread(new Runnable() {
      private ObjectInputStream in = null ;

      @Override
      public void run() {
        try {
          in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
          printDisplay(">> 입력 스트림이 열리지 않음.");
        }
        while(receiveThread == Thread.currentThread()) {
          receiveMessage();
        }
      }

      public void receiveMessage() {
        try {
          ChatMsg inMsg = (ChatMsg)in.readObject();
          if (inMsg == null) {
            disconnect();
            printDisplay(">> 서버 연결 끊김");
            return;
          }
//          printDisplay(msg);
          switch (inMsg.mode){
            case ChatMsg.MODE_TX_STRING -> {
              printDisplay(inMsg.userID + ": " + inMsg.message);
              break;
            }
            case ChatMsg.MODE_TX_FILE -> {

            }
          }
        } catch (IOException e) {
          printDisplay(">> 연결을 종료했습니다.");
        } catch (ClassNotFoundException e) {
          printDisplay(">> 잘못된 객체가 전달되었습니다.");
        }
      }

    });

    receiveThread.start();
  }

  public void disconnect() {
    send(new ChatMsg(uid, ChatMsg.MODE_LOGOUT));

    try {
      receiveThread = null;
      socket.close();
    } catch (IOException e) {
      System.err.println(">> 클라이언트 닫기 오류: " + e.getMessage());
      System.exit(-1);
    }
  }



  public void sendMessage() {
    String message = t_input.getText();
    if(message==null) return;

//    try {
//      out.write(message+"\n");
//      out.flush();
//    } catch (IOException e) {
//      System.err.println(">> 클라이언트 일반 전송 오류: "+e.getMessage());
//      System.exit(-1);
//    }
    send(new ChatMsg(uid, ChatMsg.MODE_TX_STRING, message));

    t_input.setText("");
  }

  public void sendUserID() {
    uid = t_userID.getText();
    send(new ChatMsg(uid,ChatMsg.MODE_LOGIN));
  }

  private void send(ChatMsg msg){
    try {
      out.writeObject(msg);
      out.flush();
    } catch (IOException e) {
      System.err.println(">> 클라이언트 일반 전송 오류: "+e.getMessage());
    }
  }



  public static void main(String[] args) {
    new ObjTalk("localhost", 54320);
  }

}
