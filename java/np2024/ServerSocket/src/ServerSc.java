import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ServerSc {
    private java.net.ServerSocket sc;
    private Socket c;
    private final int port = 54321;

    public ServerSc() {
        try {
            sc = new ServerSocket(port);

            while(true){
                c = sc.accept();
                new write(c).start();
                new read(c).start();
            }
        } catch (IOException e) {
            System.out.println("모든 클라이언트 연결 끊김");
        }
    }

    class read extends Thread{
        Socket c;

        public read(Socket c){
            this.c=c;
        }

        @Override
        public void run() {
            while(true){
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream(),StandardCharsets.UTF_8));
                    String i = in.readLine();
                    System.out.println(i);
                } catch (IOException e) {

                }
            }
        }
    }

    class write extends Thread{
        Socket c;

        public write(Socket c){
            this.c=c;
        }

        @Override
        public void run() {
            BufferedWriter out = null;
            try {
                out = new BufferedWriter(new OutputStreamWriter(c.getOutputStream(), StandardCharsets.UTF_8));
            } catch (IOException e) {

            }
            int i;
            Scanner s = new Scanner(System.in);
            while(true){
                String str  = s.nextLine();
                try {
                    out.write(str+"\n");
                    out.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}



class Main {
    public static void main(String[] args) throws IOException {
        new ServerSc();
    }
}

class Obj implements Serializable {
    int i;
    char c;

}