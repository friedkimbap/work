public class PauseCommand implements Command {
    private Decorder decorder;

    public PauseCommand(Decorder decorder) {
        this.decorder = decorder;
    }

    @Override
    public void execute() {
        decorder.pause();
    }
}
