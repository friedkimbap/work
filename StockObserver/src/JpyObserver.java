public class JpyObserver implements Observer {
    private double exchangeRate; // 환율

    public JpyObserver(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public void update(String stock, double priceInUsd) {
        double priceInJpy = priceInUsd * exchangeRate;
        System.out.println(stock + "의 가격은 엔화 기준 ¥" + String.format("%.2f", priceInJpy));
    }
}
