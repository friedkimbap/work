import java.io.Serializable;

public class TextMsg implements Serializable {
    String msg;

    TextMsg(String msg){
        this.msg = msg;
    }

    @Override
    public String toString(){
        return "["+msg+"]";
    }
}