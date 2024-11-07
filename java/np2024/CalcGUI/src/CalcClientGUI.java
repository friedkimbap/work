// 2071173 김영민

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DecimalFormat;

public class CalcClientGUI {
    private JFrame frame;
    private JButton b_connect, b_disconnect, b_exit, b_calc;
    private JTextField t_oper1,t_oper2,t_operation,t_result;
    private Socket socket;
    private String serverAddress;
    private int serverPort;
    private ObjectOutputStream out;
    private DataInputStream in;


    public  CalcClientGUI(){
        frame = new JFrame();
        frame.setTitle("CalcClient GUI");
        frame.setLayout(new BorderLayout());

        buildGUI();


        frame.setBounds(100,200,400,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    private void buildGUI(){
        frame.add(calcDisplayPanel(),BorderLayout.NORTH);

        JPanel interactionPanel = new JPanel();
        interactionPanel.setLayout(new BorderLayout());
        interactionPanel.add(createControlPanel(),BorderLayout.SOUTH);

        frame.add(interactionPanel,BorderLayout.SOUTH);
    }

    private void setFiledSize(JTextField jTextField){
        jTextField.setPreferredSize(new Dimension(60,16));
        jTextField.setHorizontalAlignment(JTextField.CENTER);

        jTextField.setFont(new Font("SansSerif", Font.PLAIN, 12));
    }


    private JPanel calcDisplayPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS)); // 수직으로 배치

        t_oper1 = new JTextField();
        t_oper2 = new JTextField();
        t_operation = new JTextField();
        t_result = new JTextField();
        b_calc = new JButton("계산");

        setFiledSize(t_oper1);
        setFiledSize(t_oper2);
        setFiledSize(t_operation);
        setFiledSize(t_result);
        t_operation.setPreferredSize(new Dimension(40,20));
        t_result.setEditable(false);
        b_calc.setEnabled(false);

        p.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));

        p.add(t_oper1);
        p.add(Box.createHorizontalStrut(10));
        p.add(t_operation);
        p.add(Box.createHorizontalStrut(10));
        p.add(t_oper2);
        p.add(Box.createHorizontalStrut(10));
        p.add(new JLabel("="));
        p.add(Box.createHorizontalStrut(10));
        p.add(t_result);
        p.add(Box.createHorizontalStrut(10));
        p.add(b_calc);

        t_operation.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String text = t_operation.getText();
                if(text.equals("+") || text.equals("-") || text.equals("*")
                        || text.equals("/") || text.equals("%")) {
                    b_calc.setEnabled(true);
                } else {
                    b_calc.setEnabled(false);
                }
            }
        });


        b_calc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessages();
                receiveMessages();
            }
        });

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
        serverAddress = "localhost";
        serverPort = 54321;

        try{
            socket = new Socket(serverAddress,serverPort);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("클라이언트 접속 오류> " + e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("클라이언트 접속 오류> " + e.getMessage());
            System.exit(-1);
        }
    }

    private void sendMessages (){
        try{
            out.writeObject(new CalcExpr(Double.parseDouble(t_oper1.getText())
                    , t_operation.getText().charAt(0),Double.parseDouble(t_oper2.getText())));
        } catch (IOException e) {
            System.exit(-1);
        }
    }

    private void receiveMessages(){

        try {
            double result = in.readDouble();
            DecimalFormat df = new DecimalFormat("#.##");

            t_result.setText(df.format(result));
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
}
