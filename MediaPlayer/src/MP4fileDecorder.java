public class MP4fileDecorder {
    private String filename;
    MP4fileDecorder(String filename) {
        this.filename = filename;
    }
    public void play() {
        System.out.println(filename + " 영상을 재생합니다.");
    }

    public void pause() {
        System.out.println(filename + " 영상을 일시정지합니다.");
    }
}
