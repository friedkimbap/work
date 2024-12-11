public abstract class DrinkDecorator extends Drink {
    protected Drink drink;

    public DrinkDecorator(final Drink drink) {
        this.drink = drink;
    }

    public abstract String getOrder();

    public abstract int getCost();
}
