public interface DeliverState {

  void processDelivery(); // 배송 중

  void processRefund(); // 환불

  void cancelOrder(); // 취소

  void waitForDelivery(); // 대기

  void completeDelivery(); // 배송 완료
}
