// 2071173 김영민

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CalcServerGUI {
    private JFrame frame;
    private JTextArea t_display;
    private int port;
    private ServerSocket serverSocket;
    private double result;

    public CalcServerGUI(){
        frame = new JFrame();
        frame.setTitle("CalcServer GUI");
        frame.setLayout(new BorderLayout());

        buildGUI();
        frame.setBounds(700,200,350,300);
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

                new ClientHandler(clientSocket).start();
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

    private double getResult(CalcExpr calcExpr){
        if(calcExpr.operator=='+'){
            return calcExpr.operand1+calcExpr.operand2;
        }
        else if(calcExpr.operator=='-'){
            return calcExpr.operand1-calcExpr.operand2;
        }
        else if(calcExpr.operator=='*'){
            return calcExpr.operand1*calcExpr.operand2;
        }
        else if(calcExpr.operator=='/'){
            return calcExpr.operand1/calcExpr.operand2;
        }
        else if(calcExpr.operator=='%'){
            return calcExpr.operand1%calcExpr.operand2;
        }
        else return 0;

    }

    private void receiveMessages(Socket cs){
        try {
            ObjectInputStream in = new ObjectInputStream(cs.getInputStream());

            CalcExpr message;

            while((message = (CalcExpr) in.readObject()) != null){
                result = getResult(message);
                sendMessages(cs);
                t_display.append(message.operand1+" "+message.operator+" "+message.operand2 + " = " + result + "\n");
            }


        } catch (IOException e) {
            t_display.append("클라이언트의 접속이 끊어졌습니다.\n");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendMessages(Socket cs){
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(cs.getOutputStream());
            out.writeDouble(result);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private class ClientHandler extends Thread{
        private Socket socket;

        public ClientHandler(Socket cs){
            socket = cs;
        }

        @Override
        public void run(){
            receiveMessages(socket);
            sendMessages(socket);
        }
    }

    public static void main(String[] args) {
        new CalcServerGUI();
    }
}
