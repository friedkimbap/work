public class Order {

  private DeliveryState deliveryState;
  private String productName;

  public Order(String productName) {
    this.deliveryState = new WaitForDeliveryState(this);
    this.productName = productName;

    System.out.println(productName+" 제품의 주문이 확인되었습니다.");
  }

  public String getProductName() {
    return productName;
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

  void completeOrder() {
    deliveryState.completeOrder();
  }
}
