import java.util.ArrayList;

public class EverLand {

    ArrayList<EverLandFacility> components = new ArrayList<>();

    public void addComponent(EverLandFacility component) {
        components.add(component);
    }

    public void removeComponent(EverLandFacility component) {
        components.remove(component);
    }

    public int getAveragePreference() {
        int allPreference = 0;
        for (EverLandFacility component : components) {
            allPreference += component.getPreference();
        }
        return allPreference/components.size();
    }

    public int getTotalProfit() {
        int totalProfit = 0;
        for (EverLandFacility component : components) {
            totalProfit += component.getProfit();
        }
        return totalProfit;
    }
}
