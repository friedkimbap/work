import java.util.ArrayList;
import java.util.List;

public class Stock implements Subject {
    private List<Observer> observers;
    private String name;
    private double priceInUsd;

    public Stock(String name, double priceInUsd) {
        this.name = name;
        this.priceInUsd = priceInUsd;
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(name, priceInUsd);
        }
    }

    public void setPrice(double priceInUsd) {
        this.priceInUsd = priceInUsd;
        notifyObservers();
    }
}
