public class RefundConfirmedState implements DeliveryState{ // 환불 완료 상태 클래스
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
