public class WonObserver implements Observer {
    private double exchangeRate;

    public WonObserver(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public void update(String stock, double priceInUsd) {
        double priceInWon = priceInUsd * exchangeRate;
        System.out.println(stock + " 의 가격은 한화 기준 ₩" + String.format("%.2f", priceInWon));
    }
}
