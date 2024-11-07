public class WaitForDelivery implements DeliverState {

  private Order order;

  public WaitForDelivery(Order order) {
    this.order = order;
  }

  @Override
  public void processShipping() {
    System.out.println("배송이 시작되었습니다.");
    order.setDeliverState(new WaitForDelivery(order));
  }

  @Override
  public void processRefund() {

  }

  @Override
  public void cancelOrder() {

  }

  @Override
  public void waitForDelivery() {

  }

  @Override
  public void completeDelivery() {

  }
}
