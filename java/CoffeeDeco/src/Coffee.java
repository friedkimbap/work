public class Coffee extends Drink{
    @Override
    public String getOrder() {
        return "주문 : 커피";
    }

    @Override
    public int getCost() {
        return 5000;
    }
}
