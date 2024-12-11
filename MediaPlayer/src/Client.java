public class Client {
    public static void main(String[] args) {
        MP3fileDecorder mp3Decorder = new MP3fileDecorder("song1.mp3");
        MP4fileDecorder mp4Decorder = new MP4fileDecorder("video1.mp4");

        Command playMP3 = new MP3PlayCommand(mp3Decorder);
        Command pauseMP3 = new MP3PauseCommand(mp3Decorder);
        Command playMP4 = new MP4PlayCommand(mp4Decorder);
        Command pauseMP4 = new MP4PauseCommand(mp4Decorder);

        MediaPlayer mediaPlayer = new MediaPlayer();

        mediaPlayer.setCommand(playMP3);
        mediaPlayer.play();

        mediaPlayer.setCommand(pauseMP3);
        mediaPlayer.pause();

        mediaPlayer.setCommand(playMP4);
        mediaPlayer.play();

        mediaPlayer.setCommand(pauseMP4);
        mediaPlayer.pause();
    }
}
