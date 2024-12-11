public class MP4PlayCommand implements Command {
    private MP4fileDecorder decorder;

    public MP4PlayCommand(MP4fileDecorder decorder) {
        this.decorder = decorder;
    }
    @Override
    public void execute() {
        decorder.play();
    }
}
