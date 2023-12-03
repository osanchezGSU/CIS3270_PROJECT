package GUI;

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Splash_Screen extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception  {
		
		
		
		try {
			
			
			Parent root = FXMLLoader.load(getClass().getResource("Splash_Screen.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toString());
	
			primaryStage.setTitle("AeroBookings.com");
			primaryStage.setFullScreen(false);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}