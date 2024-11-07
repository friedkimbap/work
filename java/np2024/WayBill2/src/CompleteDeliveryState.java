public class CompleteDeliveryState implements DeliveryState {

  private Manager manager;

  public CompleteDeliveryState(Manager manager) {
    this.manager = manager;
  }

  @Override
  public void orderCheck() {
    System.out.println("이미 배송 완료된 주문입니다.");
  }

  @Override
  public void startDelivery() {
    System.out.println("이미 배송 완료된 주문입니다.");
  }

  @Override
  public void processRefund() {
    System.out.println("환불 절차를 진행하겠습니다.");
  }

  @Override
  public void completeDelivery() {
    System.out.println("이미 배송 완료된 주문입니다.");
  }
}
