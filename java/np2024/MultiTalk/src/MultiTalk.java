import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class MultiTalk {
    private JTextField t_input, t_userID, t_hostAddr, t_portNum;
    private JTextArea t_display;
    private JButton b_connect, b_disconnect, b_send, b_exit;
    private String serverAddress;
    private int serverPort;
    private Socket socket;
    private BufferedWriter out;
    private BufferedReader in;
    private Thread receiveThread;

    public MultiTalk(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;

        buildGUI();

    }

    public void buildGUI() {
        JFrame frame = new JFrame();
        frame.setBounds(100,100,600,400);
        frame.setTitle("Multi Talk");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(createDisplayPanel(), BorderLayout.CENTER);

        JPanel p = new JPanel(new BorderLayout());
        p.add(createInputPanel(),BorderLayout.NORTH);
        p.add(createInfoPanel(), BorderLayout.CENTER);
        p.add(createControlPanel(), BorderLayout.SOUTH);

        frame.add(p,BorderLayout.SOUTH);
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

    public JPanel createInputPanel() {
        JPanel p = new JPanel(new BorderLayout());
        t_input = new JTextField();
        b_send = new JButton("보내기");

        p.add(t_input, BorderLayout.CENTER);
        p.add(b_send, BorderLayout.EAST);

        b_send.setEnabled(false);

        b_send.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                sendMessage();
            }
        });

        t_input.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub
                if(e.getKeyCode()==KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
                if(!t_input.getText().equals("")) {
                    b_send.setEnabled(true);
                } else {
                    b_send.setEnabled(false);
                }
            }

        });

        return p;
    }

    public JPanel createInfoPanel() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        t_userID = new JTextField(10);
        t_hostAddr = new JTextField(10);
        t_portNum = new JTextField(5);

        try {
            InetAddress local = InetAddress.getLocalHost();
            String addr = local.getHostAddress();
            String[] part = addr.split("\\.");
            t_userID.setText("guest" + part[3]);
            t_hostAddr.setText(serverAddress);
            t_portNum.setText(String.valueOf(serverPort));
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }

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
                // TODO Auto-generated method stub
                b_connect.setEnabled(false);
                b_disconnect.setEnabled(true);
                connectToServer();
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
                System.exit(0);
            }

        });

        return p;
    }

    public void printDisplay(String str){
        t_display.append(str+"\n");
        t_display.setCaretPosition(t_display.getDocument().getLength());
    }

    public String getLocalAddr() {
        String local_address = null;

        try {
            InetAddress local = InetAddress.getLocalHost();
            local_address = local.getHostAddress();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            printDisplay(e.getMessage());
            System.out.println(e.getMessage());
        }

        return local_address;
    }

    public void connectToServer() {
        try {
            socket = new Socket();
            SocketAddress sa = new InetSocketAddress(t_hostAddr.getText(),Integer.parseInt(t_portNum.getText()));
            socket.connect(sa, 3000);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            t_userID.setEditable(false);
            t_hostAddr.setEditable(false);
            t_portNum.setEditable(false);

            receiveThread = new Thread(new Runnable() {

                @Override
                public void run() {
                    sendUserID();
                    // TODO Auto-generated method stub
                    while(receiveThread==Thread.currentThread()) {
                        receiveMessage();
                    }
                }

            });

            receiveThread.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            printDisplay(e.getMessage());
            b_connect.setEnabled(true);
            b_disconnect.setEnabled(false);
            t_userID.setEditable(true);
            t_hostAddr.setEditable(true);
            t_portNum.setEditable(true);
        }
    }

    public void disconnect() {
        try {
            if(socket!=null && !socket.isClosed()) {
                if (out != null) {
                    out.write("disconnect\n");
                    out.flush();
                }
                socket.close();
            }
            receiveThread.interrupt();
            t_userID.setEditable(true);
            t_hostAddr.setEditable(true);
            t_portNum.setEditable(true);
            b_connect.setEnabled(true);
            b_disconnect.setEnabled(false);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            printDisplay(e.getMessage());
        }
    }

    public void sendMessage() {
        String str = t_input.getText();

        try {
            out.write(t_userID.getText() + ":" + str+"\n");
            out.flush();
            t_input.setText("");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            t_display.append(e.getMessage()+"\n");
        }
    }

    public void sendUserID() {
        String userID = t_userID.getText();
        String address = t_hostAddr.getText();

        try {
            out.write("/uid:"+userID+":"+ address+"\n");
            out.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            t_display.append(e.getMessage()+"\n");
        }
    }

    public void receiveMessage() {
        String str;
        try {
            while ((str = in.readLine()) != null) {
                if(str.equals("disconnect")) {
                    disconnect();
                    str="";
                } else {
                    printDisplay(str);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new MultiTalk("localhost", 54321);
    }

}
