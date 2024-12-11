public class RestaurantFacility implements EverLandFacility{
    int income;
    int preference;
    String name;
    int deposit;

    RestaurantFacility(int income, int preference, String name, int deposit){
        this.income = income;
        this.preference = preference;
        this.name = name;
        this.deposit = deposit;
    }

    @Override
    public int getProfit() {
        return deposit;
    }

    @Override
    public int getPreference() {
        return preference;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getIncome() {
        return income;
    }
}
