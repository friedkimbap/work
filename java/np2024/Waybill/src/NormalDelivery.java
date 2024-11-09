public class NormalDelivery implements DeliveryStrategy {
    @Override
    public void delivery(Ddestination d) {

        System.out.println(d.getName()+"님 "+d.getAddress()+"로 2~3일 안에 배송됩니다.");
        System.out.println("배송비는 3000원입니다.");
    }
}
