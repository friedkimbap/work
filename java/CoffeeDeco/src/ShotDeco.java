public class ShotDeco extends DrinkDecorator {
    public ShotDeco(Drink drink) {
        super(drink);
    }

    @Override
    public String getOrder() {
        return drink.getOrder() + ", 샷 추가";
    }

    @Override
    public int getCost() {
        return drink.getCost() + 500;
    }
}
