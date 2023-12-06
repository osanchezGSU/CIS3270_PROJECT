package GUI;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Splash_Screen {

    private MediaPlayer mediaPlayer;

    public void initialize() {

        String audioFilePath = "airplaneSound.mp3";
        Media media = new Media(new File(audioFilePath).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

     
        new Thread(() -> {
            try {
                Thread.sleep(2000);

                
                mediaPlayer.stop();

                Platform.runLater(() -> {

                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
