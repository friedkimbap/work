public class OrderConfirmedState implements DeliveryState { // 배송 완료 후 주문 확정 상태
    private Order order;

    public OrderConfirmedState(Order order) {
        this.order = order;
    }

    @Override
    public void startDelivery() {
        System.out.println(order.getProductName()+" 주문은 이미 확정된 주문입니다.");
    }

    @Override
    public void processRefund() {
        System.out.println(order.getProductName()+" 주문은 이미 확정된 주문입니다.");
    }

    @Override
    public void completeDelivery() {
        System.out.println(order.getProductName()+" 주문은 이미 확정된 주문입니다.");
    }

    @Override
    public void completeOrder() {
        System.out.println(order.getProductName()+" 주문은 이미 확정된 주문입니다.");
    }
}