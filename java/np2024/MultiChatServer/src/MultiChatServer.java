import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import javax.swing.*;

import java.util.Iterator;
import java.util.Vector;

public class MultiChatServer {
    private int port;
    private ServerSocket serverSocket;
    private Thread acceptThread;
    private Vector<ClientHandler> users;
    private JTextArea t_display;
    private JButton b_connect, b_disconnect, b_exit;

    public MultiChatServer(int port) {
        this.port = port;

        buildGUI();
    }

    public void buildGUI() {
        JFrame frame = new JFrame();
        frame.setBounds(700,100,500,400);
        frame.setTitle("Multi ChatServer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(createDisplayPanel(), BorderLayout.CENTER);
        frame.add(createControlPanel(), BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public JPanel createDisplayPanel() {
        JPanel p = new JPanel(new BorderLayout());
        t_display= new JTextArea();

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
                // TODO Auto-generated method stub
                b_connect.setEnabled(false);
                b_disconnect.setEnabled(true);
                acceptThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        startServer();
                    }
                });
                acceptThread.start();
            }
        });

        b_disconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                b_connect.setEnabled(true);
                b_disconnect.setEnabled(false);
                disconnect();
            }
        });

        b_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
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
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            b_connect.setEnabled(true);
            b_disconnect.setEnabled(false);
        }

        return local_address;
    }

    public void startServer() {
        Socket socket = null;
        users = new Vector<ClientHandler>();

        try {
            serverSocket = new ServerSocket(port);
            printDisplay("서버가 시작되었습니다:"+getLocalAddr());

            while(true) {
                socket = serverSocket.accept();

                ClientHandler ch = new ClientHandler(socket);

                ch.start();
                users.add(ch);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            b_connect.setEnabled(true);
            b_disconnect.setEnabled(false);
            printDisplay(e.getMessage());
        }
    }

    public void disconnect() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            acceptThread.interrupt();

            for (ClientHandler user : users) {
                user.sendMessage("disconnect");
            }
            users.clear();
        } catch (IOException e) {
            printDisplay(e.getMessage());
        }
    }


    public void printDisplay(String str) {
        t_display.append(str+"\n");
        t_display.setCaretPosition(t_display.getDocument().getLength());
    }


    // 클라이언트 핸들러 클래스
    class ClientHandler extends Thread{
        private Socket clientSocket;
        private BufferedWriter out;
        private String uid;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            try {
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                printDisplay(e.getMessage());
            }
        }

        void receiveMessage(Socket socket) {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String str;
                while((str = in.readLine())!=null) {
                    if(str.substring(0,5).equals("/uid:")) {
                        uid = str.split(":")[1];
                        String address = str.split(":")[2];
                        printDisplay("클라이언트가 연결되었습니다:"+address);
                        printDisplay("새 참가자:"+uid);
                        printDisplay("현재 참가자 수:"+users.size());
                    } else if(str.equals("disconnect")) {
                        for(int i=0;i<users.size();i++) {
                            if(users.get(i).uid.equals(uid)) {
                                users.remove(i);
                                printDisplay(uid+"님의 연결의 끊겨버렸습니다.");
                                printDisplay("현재 참가자 수:"+users.size());
                                break;
                            }
                        }
                    } else{
                        printDisplay(str);
                        broadcasting(str);
                    }

                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                printDisplay(e.getMessage());
            }
        }

        public void sendMessage(String str) {
            try {
                out.write(str+"\n");
                out.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                printDisplay(e.getMessage());
            }
        }

        public void broadcasting(String str) {
            Iterator<ClientHandler> iterator = users.iterator();
            while(iterator.hasNext()) {
                ClientHandler current = iterator.next();
                current.sendMessage(str);
            }
        }

        public void run() {
            receiveMessage(clientSocket);
        }

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new MultiChatServer(54321);
    }

}
