public class MediaPlayer {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void play() {
        command.execute();
    }

    public void pause() {
        command.execute();
    }
}
