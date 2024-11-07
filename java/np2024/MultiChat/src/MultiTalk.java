import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class MultiTalk {
    private Socket socket;
    private DataOutputStream out;
    private InputStream in;
    private JFrame frame;
    private JTextArea t_display;
    private JTextField t_input, t_address, t_id,t_portnum;
    private JButton b_connect, b_disconnect, b_send, b_exit;
    private String serverAddress;
    private int serverPort;


    public MultiTalk(){
        frame = new JFrame();
        frame.setTitle("TextMsgServer GUI");
        frame.setLayout(new BorderLayout());

        buildGUI();
        frame.setBounds(400,200,700,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    private void buildGUI(){
        frame.add(createDisplayPanel(),BorderLayout.CENTER);

        JPanel UIPanel = new JPanel();
        UIPanel.setLayout(new BorderLayout());
        UIPanel.add(createInputPanel(), BorderLayout.NORTH);
        UIPanel.add(createInfoPanel(),BorderLayout.CENTER);
        UIPanel.add(createControlPanel(),BorderLayout.SOUTH);

        frame.add(UIPanel, BorderLayout.SOUTH);
    }

    private JPanel createDisplayPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());

        t_display = new JTextArea();
        t_display.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(t_display);

        p.add(scrollPane, BorderLayout.CENTER);

        return p;
    }


    private JPanel createInputPanel(){
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());

        t_input = new JTextField();
        b_send = new JButton("보내기");

        t_input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(!t_input.getText().isEmpty()) b_send.setEnabled(true);
                if (e.getKeyCode() == KeyEvent.VK_ENTER && b_send.isEnabled()) {
                    sendMessages();
                    b_send.setEnabled(false);
                }
            }
        });

        b_send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessages();
                b_send.setEnabled(false);
            }
        });

        p.add(t_input,BorderLayout.CENTER);
        p.add(b_send,BorderLayout.EAST);

        return p;
    }

    private JPanel createInfoPanel(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1,6));

        t_address = new JTextField();
        t_id = new JTextField();
        t_portnum = new JTextField();

        JPanel p_id = new JPanel();
        p_id.setLayout(new BorderLayout());
        p_id.add(new JLabel("아이디: "),BorderLayout.WEST);
        p_id.add(t_id,BorderLayout.CENTER);

        p.add(p_id);

        JPanel p_address = new JPanel();
        p_address.setLayout(new BorderLayout());
        p_address.add(new JLabel("서버주소: "),BorderLayout.WEST);
        p_address.add(t_address,BorderLayout.CENTER);

        p.add(p_address);

        JPanel p_portnum = new JPanel();
        p_portnum.setLayout(new BorderLayout());
        p_portnum.add(new JLabel("포트번호: "),BorderLayout.WEST);
        p_portnum.add(t_portnum,BorderLayout.CENTER);

        p.add(p_portnum);

        return p;
    }

    private JPanel createControlPanel(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1,3));

        b_connect = new JButton("접속하기");
        b_disconnect = new JButton("접속 끊기");
        b_exit = new JButton("종료하기");
        b_disconnect.setEnabled(false);

        b_connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b_disconnect.setEnabled(true);
                b_connect.setEnabled(false);
                connectToServer();
            }
        });

        b_disconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b_disconnect.setEnabled(false);
                b_connect.setEnabled(true);
                disconnect();
            }
        });

        b_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        p.add(b_connect);
        p.add(b_disconnect);
        p.add(b_exit);

        return p;
    }

    private void connectToServer() {
        serverAddress = t_address.getText();
        serverPort = Integer.parseInt(t_portnum.getText());

        try{
            t_display.append("서버에 접속하였습니다.\n");
            sendUserID();
            socket = new Socket();
            SocketAddress sa = new InetSocketAddress(serverAddress,serverPort);
            socket.connect(sa,3000);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            if(!t_input.getText().isEmpty()) b_send.setEnabled(true);
        } catch (UnknownHostException e) {
            System.err.println("클라이언트 접속 오류> " + e.getMessage());
            t_display.append("서버가 존재하지 않습니다\n");
            b_connect.setEnabled(true);
            b_disconnect.setEnabled(false);
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("클라이언트 접속 오류> " + e.getMessage());
            t_display.append("서버가 존재하지 않습니다\n");
            b_connect.setEnabled(true);
            b_disconnect.setEnabled(false);
        }
    }

    private void sendMessages (){
        try{
            out.writeBytes(t_input.getText());
        } catch (IOException e) {
            t_display.append("메시지 전송 중 오류가 발생했습니다: " + e.getMessage() + "\n");
            b_connect.setEnabled(true);
            b_disconnect.setEnabled(false);
            e.printStackTrace();
        }

        t_display.setCaretPosition(t_display.getDocument().getLength());
        t_input.setText("");
    }

    private void sendUserID(){
        try {
            out.writeBytes(t_id.getText());
        } catch (IOException e) {
            t_display.append("ID가 이상해요 : " + e.getMessage() + "\n");
            b_connect.setEnabled(true);
            b_disconnect.setEnabled(false);
            e.printStackTrace();
        }
    }

    private void recieveMessages(){
        try {
            in = socket.getInputStream();
            t_display.append(String.valueOf(in.read()));
            if(in.readLine()==null){
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void disconnect(){
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    class recieveHandler extends Thread{
        @Override
        void run(){
            recieveMessages();
        }

    }


    public static void main(String[] args) {
        new MultiTalk();
    }
}
