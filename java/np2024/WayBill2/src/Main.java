public class Main {

  public static void main(String[] args) { // 쇼핑몰 매니저의 주문 현황에 대한 절차
    Order bag = new Order("가방");
    bag.startDelivery();
    bag.completeDelivery();
    bag.processRefund();
    bag.completeDelivery();

    Order shoes = new Order("신발");
    shoes.startDelivery();
    shoes.completeDelivery();
    shoes.completeOrder();
  }
}