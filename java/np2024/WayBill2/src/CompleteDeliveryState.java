public class CompleteDeliveryState implements DeliveryState { // 배송 완료 상태

  private Order order;

  public CompleteDeliveryState(Order order) {
    this.order = order;
  }

  @Override
  public void startDelivery() {
    System.out.println(order.getProductName()+" 주문은 이미 배송 완료된 주문입니다.");
  }

  @Override
  public void processRefund() {
    System.out.println(order.getProductName()+" 주문의 환불 절차를 진행하겠습니다.");
    System.out.println("이미 배송이 완료되었기 때문에 배송비 6000원을 제외하고 입금합니다.");
    order.setDeliveryState(new RefundConfirmedState(order));
  }

  @Override
  public void completeDelivery() {
    System.out.println(order.getProductName()+" 주문은 이미 배송 완료된 주문입니다.");
  }

  @Override
  public void completeOrder() {
    System.out.println(order.getProductName()+" 주문을 확정하였습니다.");
    System.out.println(order.getProductName()+" 주문은 앞으로 환불이 불가능합니다.");
    order.setDeliveryState(new CompleteDeliveryState(order));
  }
}
