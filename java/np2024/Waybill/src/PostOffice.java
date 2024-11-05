import javax.print.attribute.standard.Destination;

public class PostOffice { // 우체국
    private DeliveryStrategy diliveryStrategy;

    private PostOffice(){

    }

    public void delivery(Ddestination d){
        System.out.println(d.getName()+"님"+d.getAddress()+"로 택배를 보냅니다.");
    }
}
