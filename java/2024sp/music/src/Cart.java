import java.util.ArrayList;

public class Cart {
    private ArrayList<Song> Songs = new ArrayList<>();
    private int calPrice;

    public int CalSongs(){
        for(int i=0;i<Songs.size();i++){
            calPrice+=Songs.get(i).getPrice();
        }
    }
}
