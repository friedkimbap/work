public class MilkDeco extends DrinkDecorator {
    public MilkDeco(Drink drink) {
        super(drink);
    }

    @Override
    public String getOrder() {
        return drink.getOrder() + ", 우유 추가";
    }

    @Override
    public int getCost() {
        return drink.getCost() + 1000;
    }
}
