public class EverLandReport {
    public static void main(String[] args) {
        AttractionSection attractionSection = new AttractionSection();

        AttractionFacility TExpress = new AttractionFacility(1030,85,"T익스프레스", 350);
        AttractionFacility AmazonExpress = new AttractionFacility(790,72,"아마존 익스프레스", 420);
        AttractionFacility ColumbusExplorer = new AttractionFacility(680,60,"콜럼버스 대탐험", 230);

        attractionSection.addComponent(TExpress);
        attractionSection.addComponent(AmazonExpress);
        attractionSection.addComponent(ColumbusExplorer);

        System.out.println(attractionSection.getMostPreferenceAttractionFacility().name+"이 가장 인기 많은 어트랙션입니다.");
        System.out.println("어트랙션 섹션의 총 수입은 "+attractionSection.getAllProfit() + "만 원입니다.");
        System.out.println("어트랙션 섹션의 평균 선호도는 " +attractionSection.getAveragePreference() + "입니다.");

        RestaurantSection restaurantSection = new RestaurantSection();

        RestaurantFacility BurgerKing = new RestaurantFacility(2152,91,"버거킹",200);
        RestaurantFacility StarBucks = new RestaurantFacility(1343, 85, "스타벅스", 400);
        RestaurantFacility Vips = new RestaurantFacility(1464, 68, "빕스", 600);

        restaurantSection.addComponent(BurgerKing);
        restaurantSection.addComponent(StarBucks);
        restaurantSection.addComponent(Vips);

        System.out.println(restaurantSection.getMostSellsRestaurant().name+"이 가장 많은 수익을 낸 식당입니다");
        System.out.println("레스토랑 섹션에서 벌어들인 총 계약금은 "+restaurantSection.getAllProfit() + "만 원입니다.");
        System.out.println("레스토랑 섹션의 평균 선호도는 " +restaurantSection.getAveragePreference() + "입니다.");
    }
}