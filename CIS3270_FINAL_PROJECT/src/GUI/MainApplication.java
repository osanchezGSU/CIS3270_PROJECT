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
       
            FXMLLoader splashLoader = new FXMLLoader(getClass().getResource("Splash_Screen.fxml"));
            Parent splashRoot = splashLoader.load();
            Scene splashScene = new Scene(splashRoot, 600, 385);

           
            Splash_Screen splashController = splashLoader.getController();
            splashController.initialize();

            
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
             
                FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), splashRoot);
                fadeOut.setFromValue(1);
                fadeOut.setToValue(0);
                fadeOut.setOnFinished(fadeEvent -> {
                   
                    try {
                        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/GUI/Welcome.fxml"));
                        Parent mainRoot = mainLoader.load();
                        Scene mainScene = new Scene(mainRoot);
                        String css = this.getClass().getResource("application.css").toExternalForm();
                        mainScene.getStylesheets().add(css);
                       
                        primaryStage.setScene(mainScene);
                        primaryStage.setTitle("AeroBookings.com");

                       
                        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), mainRoot);
                        fadeIn.setFromValue(0);
                        fadeIn.setToValue(1);
                        fadeIn.play();
                        
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

              
                fadeOut.play();
            });

           
            primaryStage.setScene(splashScene);
            primaryStage.show();

          
            pause.play();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}