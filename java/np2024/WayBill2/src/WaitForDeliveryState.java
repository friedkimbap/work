public class WaitForDeliveryState implements DeliveryState { // 주문 대기 상태

  private Order order;

  public WaitForDeliveryState(Order order) {
    this.order = order;
  }

  @Override
  public void startDelivery() {
    System.out.println(order.getProductName()+" 주문의 배송이 시작되었습니다.");
    order.setDeliveryState(new ProcessDeliveryState(order));
  }

  @Override
  public void processRefund() {
    System.out.println(order.getProductName()+" 주문의 환불이 완료되었습니다.");
    order.setDeliveryState(new RefundConfirmedState(order));
  }

  @Override
  public void completeDelivery() {
    System.out.println(order.getProductName()+" 주문의 배송이 시작되지 않았습니다.");
  }

  @Override
  public void completeOrder() {
    System.out.println(order.getProductName()+" 주문은 아직 배송이 완료되지 않았습니다");
  }
}
