import java.util.ArrayList;

public class AttractionSection extends EverLandSection{
    ArrayList<AttractionFacility> attractionFacilities = new ArrayList<>();

    @Override
    public void addComponent(EverLandFacility facility) {
        attractionFacilities.add((AttractionFacility) facility);
    }

    @Override
    public void removeComponent(EverLandFacility facility) {
        attractionFacilities.remove(facility);
    }

    @Override
    public int getAveragePreference() {
        int allPreference = 0;
        for (EverLandFacility attraction : attractionFacilities) {
            allPreference += attraction.getPreference();
        }
        return allPreference/ attractionFacilities.size();
    }

    @Override
    public int getAllProfit() {
        int totalProfit = 0;
        for (EverLandFacility attraction : attractionFacilities) {
            totalProfit += attraction.getProfit();
        }
        return totalProfit;
    }

    public AttractionFacility getMostPreferenceAttractionFacility() {
        int mostPreference = 0;
        int index = 0;
        for (AttractionFacility attraction : attractionFacilities) {
            if (attraction.getPreference() > attractionFacilities.get(mostPreference).getPreference()) {
                mostPreference = index;
            }
            index++;
        }

        return attractionFacilities.get(mostPreference);
    }
}
