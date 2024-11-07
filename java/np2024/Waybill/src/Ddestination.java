import java.util.Random;

public class Ddestination { // 배송자
    private String address;
    private String name;
    private DeliveryStrategy deliveryStrategy;

    public Ddestination(String address, String name, DeliveryStrategy deliveryStrategy) {
        this.address = address;
        this.name = name;
        this.deliveryStrategy = deliveryStrategy;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public DeliveryStrategy getDeliveryStrategy(){
        return deliveryStrategy;
    }
}
