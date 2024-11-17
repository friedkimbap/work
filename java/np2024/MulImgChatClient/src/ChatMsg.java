// 2071173 김영민
import java.io.Serial;
import java.io.Serializable;
import javax.swing.ImageIcon;

public class ChatMsg implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  public final static int MODE_LOGIN = 0x1;
  public final static int MODE_LOGOUT = 0x2;
  public final static int MODE_TX_STRING = 0x10;
  public final static int MODE_TX_FILE = 0x20;
  public final static int MODE_TX_IMAGE = 0x40;

  String userID;
  int mode;
  String message;
  ImageIcon image;
  long size;

  public ChatMsg(String userID, int mode, String message, ImageIcon image, long size){
    this.userID = userID;
    this.mode = mode;
    this.message = message;
    this.image = image;
    this.size = size;
  }

  public ChatMsg(String userID, int mode, String message, ImageIcon icon){
    this(userID,mode,message,icon,0);
  }

  public ChatMsg(String userID, int mode, String message){
    this(userID,mode,message,null);
  }

//  public ChatMsg(String userID, int mode, ImageIcon icon){
//    this(userID,mode,null,icon);
//  }

  public ChatMsg(String userID, int mode, String filename, long size){
    this(userID,mode,filename,null, size);
  }

  public ChatMsg(String userID, int mode){
    this(userID,mode,null,null);
  }

}
