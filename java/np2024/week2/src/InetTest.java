import java.net.InetAddress;
import java.net.UnknownHostException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class InetTest {
    public static void main(String[] args) {
        InetAddress local = null;
        InetAddress hs = null;

        try {
            local = InetAddress.getLocalHost();
            byte[] addr = {(byte) 220,(byte) 66,(byte) 102,(byte) 11};
            hs = InetAddress.getByAddress(addr);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        /*try {
            hs = InetAddress.getByName("www.hansung.ac.kr");
        } catch (UnknownHostException e) {
            System.out.println("유효하지 않은 주소");
            System.exit(1);
            throw new RuntimeException(e);
        }*/

        System.out.println("로컬 호스트 메소드 이름 :"+local.getHostName());
        System.out.println("로컬 호스트 IP 주소 :"+local.getHostAddress());
        System.out.println("한성대학교 메소드 이름 :"+hs.getHostName());
        System.out.println("한성대학교 IP 주소 :"+hs.getHostAddress());
    }
}