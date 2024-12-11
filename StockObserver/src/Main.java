public class Main {
    public static void main(String[] args) {
        Stock appleStock = new Stock("Apple Inc.", 150.0);

        UsdObserver usdObserver = new UsdObserver();
        KrwObserver krwObserver = new KrwObserver(1300.0);
        JpyObserver jpyObserver = new JpyObserver(110.0);

        appleStock.registerObserver(usdObserver);
        appleStock.registerObserver(krwObserver);
        appleStock.registerObserver(jpyObserver);

        appleStock.setPrice(155.0);
        appleStock.setPrice(160.0);
    }
}
