public class AttractionFacility implements EverLandFacility {
    int income;
    int preference;
    String name;
    int managementCost;

    AttractionFacility(int income, int preference , String name, int managementCost) {
        this.income = income;
        this.name = name;
        this.preference = preference;
        this.managementCost = managementCost;
    }

    @Override
    public int getProfit() {
        return income - managementCost;
    }

    @Override
    public int getPreference() {
        return preference;
    }

    @Override
    public String getName() {
        return name;
    }
}
