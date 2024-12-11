public class CreamDeco extends DrinkDecorator{
    public CreamDeco(Drink drink) {
        super(drink);
    }

    @Override
    public String getOrder() {
        return drink.getOrder() + ", 크림 추가";
    }

    @Override
    public int getCost() {
        return drink.getCost() + 500;
    }
}
