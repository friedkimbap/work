public class MP4PauseCommand implements Command {
    private MP4fileDecorder decorder;

    public MP4PauseCommand(MP4fileDecorder decorder) {
        this.decorder = decorder;
    }

    @Override
    public void execute() {
        decorder.pause();
    }
}
