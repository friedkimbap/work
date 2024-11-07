public class QuickService implements DeliveryStrategy{
    @Override
    public void delivery(Ddestination d) {
        System.out.println(d.getName()+"님"+d.getAddress()+"로 오늘 바로 배송됩니다.");
        System.out.println("배송비는 20000원 입니다.");
    }
}
