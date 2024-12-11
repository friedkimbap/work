public class UsdObserver implements Observer {
    @Override
    public void update(String stock, double priceInUsd) {
        System.out.println("The price of " + stock + " is now $" + priceInUsd);
    }
}
