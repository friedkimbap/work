public class Order {

  private DeliveryState deliveryState;

  public Order() {
    this.deliveryState = new WaitForDeliveryState(this);
  }

  public void setDeliveryState(DeliveryState deliveryState) {
    this.deliveryState = deliveryState;
  }

  void startDelivery() {
    deliveryState.startDelivery();
  }

  void processRefund() {
    deliveryState.processRefund();
  }

  void completeDelivery() {
    deliveryState.completeDelivery();
  }
}
