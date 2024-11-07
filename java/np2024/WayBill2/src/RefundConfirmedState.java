public class RefundConfirmedState implements DeliveryState{
    private Order order;

    public RefundConfirmedState(Order order) {
        this.order = order;
    }

    @Override
    public void startDelivery() {
        System.out.println(order.getProductName()+" 주문은 이미 환불 완료된 주문입니다.");
    }

    @Override
    public void processRefund() {
        System.out.println(order.getProductName()+" 주문은 이미 환불 완료된 주문입니다.");
    }

    @Override
    public void completeDelivery() {
        System.out.println(order.getProductName()+" 주문은 이미 환불 완료된 주문입니다.");
    }

    @Override
    public void completeOrder() {
        System.out.println(order.getProductName()+" 주문은 이미 환불 완료된 주문입니다.");
    }
}
