public class Coupang { // 배달업체
    private DeliveryStrategy diliveryStrategy;

    public Coupang(){

    }

    public void setDiliveryStrategy(Ddestination d) {
        this.diliveryStrategy = d.getDeliveryStrategy();
    }

    public void deliver(Ddestination d){
        setDiliveryStrategy(d);
        diliveryStrategy.delivery(d);
    }
}
