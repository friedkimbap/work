public class Main {
    public static void main(String[] args) {
        AndroidStudio VM = new VMAndroidStudio();
        VM.startAS();
        VM.codingASApp();
        VM.compileASApp();
        VM.runASApp();

        AndroidStudio Adb = new AdbAndroidStudio();
        Adb.startAS();
        Adb.codingASApp();
        Adb.compileASApp();
        Adb.runASApp();
    }
}