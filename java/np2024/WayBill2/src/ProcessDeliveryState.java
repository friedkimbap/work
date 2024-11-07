public class ProcessDeliveryState implements DeliveryState {

  private Manager manager;

  public ProcessDeliveryState(Manager manager) {
    this.manager = manager;
  }

  @Override
  public void orderCheck() {

  }

  @Override
  public void startDelivery() {

  }

  @Override
  public void processRefund() {

  }

  @Override
  public void completeDelivery() {
    System.out.println("배송이 완료되었습니다.\n");
    manager.setDeliveryState(new CompleteDeliveryState(manager));
  }
}
