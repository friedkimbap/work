import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class client {
    private Socket c;
    private BufferedWriter out;
    private int port = 54321;
    private String domain = "localhost";
    private String str;
    private Thread t;

    public client(){
        try {
            c=new Socket(domain, port);
            out = new BufferedWriter(new OutputStreamWriter(c.getOutputStream(),StandardCharsets.UTF_8));
             t = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(t==Thread.currentThread()){
                        receiveMessages();
                    }
            }});
            t.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while(true){
            sendMessages();
        }
    }

    private void sendMessages(){
        Scanner s = new Scanner(System.in);
        str=s.nextLine();
        try {
            out.write(str+"\n");
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void receiveMessages(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream(),StandardCharsets.UTF_8));
            System.out.println(in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
class Main {
    public static void main(String[] args) {
        new client();
    }
}

class Obj implements Serializable {
    int i;
    char c;
}