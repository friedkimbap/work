import java.util.ArrayList;

public class PostOffice { // 우체국
    private DeliveryStrategy diliveryStrategy;

    public PostOffice(){

    }

    public void setDiliveryStrategy(Ddestination d) {
        this.diliveryStrategy = d.getDeliveryStrategy();
    }

    public void deliver(Ddestination d){
        setDiliveryStrategy(d);
        diliveryStrategy.delivery(d);
    }
}
