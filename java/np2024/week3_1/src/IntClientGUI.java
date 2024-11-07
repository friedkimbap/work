import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class IntClientGUI {
    private JFrame frame;
    private JTextArea t_display;
    private JTextField t_input;
    private JButton b_connect, b_disconnect, b_send, b_exit;
    private Socket socket;
    private String serverAddress;
    private int serverPort;
    private DataOutputStream out;


    public  IntClientGUI(){
        frame = new JFrame();
        frame.setTitle("IntClient GUI");
        frame.setLayout(new BorderLayout());

        buildGUI();


        frame.setBounds(100,200,300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    private void buildGUI(){
        frame.add(createDisplayPanel(),BorderLayout.CENTER);

        JPanel interactionPanel = new JPanel();
        interactionPanel.setLayout(new BorderLayout());
        interactionPanel.add(createInputPanel(),BorderLayout.NORTH);
        interactionPanel.add(createControlPanel(),BorderLayout.SOUTH);

        frame.add(interactionPanel,BorderLayout.SOUTH);
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
        serverAddress = "127.0.0.1";
        serverPort = 54321;

        try{
            t_display.append("서버에 접속하였습니다.\n");
            socket = new Socket(serverAddress,serverPort);
            out = new DataOutputStream(socket.getOutputStream());
            if(!t_input.getText().isEmpty()) b_send.setEnabled(true);
        } catch (UnknownHostException e) {
            System.err.println("클라이언트 접속 오류> " + e.getMessage());
            t_display.append("서버가 존재하지 않습니다\n");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("클라이언트 접속 오류> " + e.getMessage());
            System.exit(-1);
        }
    }

    private void sendMessages (){
        try{
            out.writeInt(Integer.parseInt(t_input.getText()));
        } catch (IOException e) {
            System.exit(-1);
            e.printStackTrace();
        } catch (NumberFormatException e){
            t_display.append("숫자만 입력하시오\n");
        }

        t_display.append("나 : "+t_input.getText() + "\n");
        t_display.setCaretPosition(t_display.getDocument().getLength());
        t_input.setText("");
    }

    private void disconnect(){
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        new IntClientGUI();
    }
}
