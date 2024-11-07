import java.util.Calendar;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

    }
}

class MP3 {
    public void playSong(){

    };
}

class TimeReminder {
    private MP3 m;

    public void reminder() {
        Calendar cal = Calendar.getInstance();
        m = new MP3();
        int hour = cal.get(Calendar.HOUR_OF_DAY);

        if(hour>=22){
            m.playSong();
        }
    }
}

class TimeTest implements TimeSetter{


    @Override
    public void setTime(int hour) {
        
    }

    @Override
    public int getTime() {
        return 0;
    }
}