import java.util.ArrayList;

public class RestaurantSection extends EverLandSection {
    ArrayList<RestaurantFacility> restaurantFacilities = new ArrayList<>();


    @Override
    public void addComponent(EverLandFacility facility) {
        restaurantFacilities.add((RestaurantFacility) facility);
    }

    @Override
    public void removeComponent(EverLandFacility facility) {
        restaurantFacilities.remove(facility);
    }

    @Override
    public int getAveragePreference() {
        int allPreference = 0;
        for (EverLandFacility restaurant : restaurantFacilities) {
            allPreference += restaurant.getPreference();
        }
        return allPreference/ restaurantFacilities.size();
    }

    @Override
    public int getAllProfit() {
        int totalProfit = 0;
        for (EverLandFacility restaurant : restaurantFacilities) {
            totalProfit += restaurant.getProfit();
        }
        return totalProfit;
    }

    public RestaurantFacility getMostSellsRestaurant() {
        int mostSells = 0;
        int index = 0;
        for (RestaurantFacility restaurant : restaurantFacilities) {
            if (restaurant.getIncome() > restaurantFacilities.get(mostSells).getIncome()) {
                mostSells = index;
            }
            index++;
        }

        return restaurantFacilities.get(mostSells);
    }
}
