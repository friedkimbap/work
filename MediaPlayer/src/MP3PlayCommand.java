public class MP3PlayCommand implements Command {
    private MP3fileDecorder decorder;

    public MP3PlayCommand(MP3fileDecorder decorder) {
        this.decorder = decorder;
    }
    @Override
    public void execute() {
        decorder.play();
    }
}
