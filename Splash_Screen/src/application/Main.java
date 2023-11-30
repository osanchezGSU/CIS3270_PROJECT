package application;
	
import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		File file = new File("airplane-fly-over-01.mp3");
		Player player = new Player(file.toURI().toString());
		player.mediaplayer.play();
		try {
			Image image = new Image(new File("airplane.gif").toURI().toString());
			ImageView imageview = new ImageView(image);
			
			Group root = new Group(imageview);
			Scene scene = new Scene(root);
			
			
			Image icon = new Image(new File("icon.png").toURI().toString());
			primaryStage.getIcons().add(icon);
		
			primaryStage.setTitle("AeroBookings.com");
			primaryStage.setFullScreen(true);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
