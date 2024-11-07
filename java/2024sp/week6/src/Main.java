public class Main {
    public static void main(String[] args)
    {
        System.out.println("Hello world!");
    }
}

class Theme {
    private static Theme instance;
    private String themeColor;

    private Theme(){
        this.themeColor = "Light";
    }

    public synchronized static Theme getInstance() {
        if (instance == null){
            instance = new Theme();
        }
        return instance;
    }
}