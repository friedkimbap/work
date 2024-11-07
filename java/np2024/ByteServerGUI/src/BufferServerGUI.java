import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BufferServerGUI {
    private JFrame frame;
    private JTextArea t_display;
    private int port;
    private ServerSocket serverSocket;

    public BufferServerGUI(){
        frame = new JFrame();
        frame.setTitle("BufferServer GUI");
        frame.setLayout(new BorderLayout());

        buildGUI();
        frame.setBounds(400,200,300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        startServer();
    }

    private void buildGUI(){
        frame.add(createDisplayPanel(),BorderLayout.CENTER);
        frame.add(createControlPanel(),BorderLayout.SOUTH);
    }

    private JPanel createDisplayPanel(){
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        t_display = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(t_display);

        t_display.setEditable(false);

        p.add(scrollPane,BorderLayout.CENTER);

        return p;
    }

    private JPanel createControlPanel(){
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        JButton btn = new JButton("종료");

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        p.add(btn,BorderLayout.CENTER);

        return p;
    }

    private void startServer(){
        port = 54321;
        Socket clientSocket = null;
        try {
            serverSocket=new ServerSocket(port);
            t_display.setText("서버가 시작되었습니다\n");


            while(true){
                clientSocket=serverSocket.accept();
                t_display.append("클라이언트가 접속하였습니다\n");

                receiveMessages(clientSocket);
            }
        } catch (IOException e) {
            System.err.print("서버 연결에 실패하였습니다 > "+e.getMessage());
            System.exit(-1);
        }
    }

    private void printDisplay(String msg){
        t_display.append("클라이언트 : "+msg+"\n");
        t_display.setCaretPosition(t_display.getDocument().getLength());
    }

    private void receiveMessages(Socket cs){
        try {
            DataInputStream in = new DataInputStream(new BufferedInputStream (cs.getInputStream()));

            int message;
            while((message = in.readInt())!=0){
                if(message==-1){
                    System.exit(-1);
                }
                printDisplay(String.valueOf(message));
            }

        } catch (IOException e) {
            t_display.append("클라이언트의 접속이 끊어졌습니다.\n");
        }
    }

    public static void main(String[] args) {
        new BufferServerGUI();
    }
}
