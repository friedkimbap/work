import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ByteServerGUI {
    private JFrame frame;
    private JTextArea textArea;
    private int port;
    private ServerSocket serverSocket;

    public  ByteServerGUI(){
        frame = new JFrame();
        frame.setTitle("ByteClient GUI");
        frame.setLayout(new BorderLayout());

        buildGUI();


        frame.setBounds(400,200,300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    private void buildGUI(){
        frame.add(createDisplayPanel(),BorderLayout.CENTER);
        frame.add(createControlPanel(),BorderLayout.SOUTH);
        startServer();
    }

    private JPanel createDisplayPanel(){
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        textArea.setEditable(false);

        p.add(textArea,BorderLayout.CENTER);
        p.add(scrollPane,BorderLayout.EAST);

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
        Socket clientSocket = null;
        try {
            serverSocket=new ServerSocket(port);

            while(true){
                clientSocket=serverSocket.accept();
                textArea.setText("서버가 시작되었습니다");

                receiveMessages(clientSocket);
            }
        } catch (IOException e) {
            System.err.print("서버 연결에 실패하였습니다 > "+e.getMessage());
            System.exit(-1);
        }
    }

    private void printDisplay(String msg){
        textArea.append("클라이언트 : "+msg+"\n");
    }

    private void receiveMessages(Socket cs){
        try {
            InputStream in = cs.getInputStream();

            int message;
            while((message = in.read())!=0){
                printDisplay(String.valueOf(message));
            }

        } catch (IOException e) {
            System.err.println("클라이언트에 문제가 있습니다 > " + e.getMessage());
            System.exit(-1);
        }
    }
}
