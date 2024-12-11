public class Main {
    public static void main(String[] args) {
        Drink Coffee = new CreamDeco(new MilkDeco(new Coffee()));
        System.out.println(Coffee.getOrder());
        System.out.println(Coffee.getCost());

        Drink Icetea = new ShotDeco(new Icetea());
        System.out.println(Icetea.getOrder());
        System.out.println(Icetea.getCost());
    }
}