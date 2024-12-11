public class UsdObserver implements Observer {
    @Override
    public void update(String stock, double priceInUsd) {
        System.out.println(stock + "의 가격은 미국 달러 기준 $" + priceInUsd);
    }
}
