import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ByteClientGUI {
    private final JFrame frame;

    public  ByteClientGUI(){
        frame = new JFrame();
        frame.setTitle("ByteClient GUI");
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

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);

        p.add(textArea,BorderLayout.CENTER);
        p.add(scrollPane,BorderLayout.EAST);

        return p;
    }

    private JPanel createInputPanel(){
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());

        JTextField textField = new JTextField();
        JButton btn = new JButton("보내기");
        btn.setEnabled(false);

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                btn.setEnabled(!textField.getText().isEmpty());
            }
        });

        p.add(textField,BorderLayout.CENTER);
        p.add(btn,BorderLayout.EAST);

        return p;
    }

    private JPanel createControlPanel(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1,3));

        JButton conBtn = new JButton("접속하기");
        JButton disBtn = new JButton("접속 끊기");
        JButton exitBtn = new JButton("종료하기");
        disBtn.setEnabled(false);

        conBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disBtn.setEnabled(true);
                conBtn.setEnabled(false);
            }
        });

        disBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disBtn.setEnabled(false);
                conBtn.setEnabled(true);
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        p.add(conBtn);
        p.add(disBtn);
        p.add(exitBtn);

        return p;
    }



}

