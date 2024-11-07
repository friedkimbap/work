public class ProcessDeliveryState implements DeliveryState { // 배송 진행 상태

  private Order order;

  public ProcessDeliveryState(Order order) {
    this.order = order;
  }

  @Override
  public void startDelivery() {
    System.out.println(order.getProductName()+" 주문은 이미 배송을 시작했습니다.");
  }

  @Override
  public void processRefund() {
    System.out.println(order.getProductName()+" 주문의 환불 절차를 진행하겠습니다.");
    System.out.println("배송비 3000원을 제외하고 입금됩니다.");
    order.setDeliveryState(new RefundConfirmedState(order));
  }

  @Override
  public void completeDelivery() {
    System.out.println(order.getProductName()+" 주문의 배송이 완료되었습니다.");
    order.setDeliveryState(new CompleteDeliveryState(order));
  }

  @Override
  public void completeOrder() {
    System.out.println(order.getProductName()+" 주문은 아직 배송이 완료되지 않았습니다");
  }
}
