package GUI;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainApplication extends Application {
	
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load splash screen
            FXMLLoader splashLoader = new FXMLLoader(getClass().getResource("Splash_Screen.fxml"));
            Parent splashRoot = splashLoader.load();
            Scene splashScene = new Scene(splashRoot);

            // Set up audio in the SplashScreenController
            Splash_Screen splashController = splashLoader.getController();
            splashController.initialize();

            // Create a pause before the fade-out animation
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> {
                // Create a fade transition for the splash screen
                FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), splashRoot);
                fadeOut.setFromValue(1);
                fadeOut.setToValue(0);
                fadeOut.setOnFinished(fadeEvent -> {
                    // Load the main application after the fade-out animation
                    try {
                        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/GUI/Welcome.fxml"));
                        Parent mainRoot = mainLoader.load();
                        Scene mainScene = new Scene(mainRoot);

                        // Set up the main stage
                        primaryStage.setScene(mainScene);
                        primaryStage.setTitle("Main Application");

                        // Show the main scene with a fade-in animation
                        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), mainRoot);
                        fadeIn.setFromValue(0);
                        fadeIn.setToValue(1);
                        fadeIn.play();
                        
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                // Play the fade-out animation
                fadeOut.play();
            });

            // Show the splash screen
            primaryStage.setScene(splashScene);
            primaryStage.show();

            // Start the delay before transitioning
            pause.play();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}