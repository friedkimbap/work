public class WaitForDeliveryState implements DeliveryState {

  private Manager manager;

  public WaitForDeliveryState(Manager manager) {
    this.manager = manager;
  }

  @Override
  public void orderCheck() {
    System.out.println("이미 주문 확인을 하였습니다.\n");
  }

  @Override
  public void startDelivery() {
    System.out.println("배송이 시작되었습니다.\n");
    manager.setDeliveryState(new ProcessDeliveryState(manager));
  }

  @Override
  public void processRefund() {
    System.out.println("환불이 완료되었습니다.\n");
  }

  @Override
  public void completeDelivery() {
    System.out.println("배송이 시작되지 않았습니다.\n");
  }
}
