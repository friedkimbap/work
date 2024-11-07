public class RocketDelivery implements DeliveryStrategy {

    @Override
    public void delivery(Ddestination d) {
        System.out.println(d.getName()+"님"+d.getAddress()+"로 내일까지 배송됩니다.");
        System.out.println("배송비는 8000원 입니다.");
    }
}
