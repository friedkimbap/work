public class MP3fileDecorder {
    private String filename;
    MP3fileDecorder(String filename){
        this.filename = filename;
    }

    public void play() {
        System.out.println(filename + " 음악을 재생합니다.");
    }

    public void pause() {
        System.out.println(filename + " 음악을 일시정지합니다.");
    }
}
