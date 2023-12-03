package GUI;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Splash_Screen {

    private MediaPlayer mediaPlayer;

    public void initialize() {
        // Load and play audio
        String audioFilePath = "/Users/osvaldosanchez/Downloads/airplane_sound.mp3";
        Media media = new Media(new File(audioFilePath).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        // Simulate loading tasks (replace this with your actual loading logic)
        new Thread(() -> {
            try {
                Thread.sleep(3000);

                // Stop the audio when the loading is done
                mediaPlayer.stop();

                // Close the stage on the JavaFX Application Thread
                Platform.runLater(() -> {
                    // Assuming you have a reference to the stage, replace "yourStage" with the actual variable name
                    // yourStage.close();
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
