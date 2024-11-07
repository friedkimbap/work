public interface DeliveryState {

  void orderCheck();

  void startDelivery(); // 배송 중

  void processRefund(); // 환불

  void completeDelivery(); // 배송 완료
}
