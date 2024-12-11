public class CookieDeco extends DrinkDecorator{
    public CookieDeco(Drink drink) {
        super(drink);
    }

    @Override
    public String getOrder() {
        return drink.getOrder() + ", 쿠키 추가";
    }

    @Override
    public int getCost() {
        return 0;
    }
}
