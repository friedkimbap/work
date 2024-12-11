public class MP3PauseCommand implements Command {
    private MP3fileDecorder decorder;

    public MP3PauseCommand(MP3fileDecorder decorder) {
        this.decorder = decorder;
    }

    @Override
    public void execute() {
        decorder.pause();
    }
}
