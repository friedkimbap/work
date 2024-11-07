public class Console {
    private String manufacturer;
    private packStategy ps;

    public Console(String manufacturer){
        this.manufacturer=manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setPackStategy(packStategy ps){
        this.ps=ps;
    }

    public void insert(){
        ps.start();
    }
}

interface packStategy{
    String name = null;

    default void start(){
        System.out.println(name+"시작");
    }
}