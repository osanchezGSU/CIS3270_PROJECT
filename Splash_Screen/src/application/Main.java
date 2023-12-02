package application;
	
import java.io.File;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	public static Stage primaryStage = null;
	
	@Override
	public void start(Stage primaryStage) throws Exception  {
		
		Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		
		primaryStage.show();
		this.primaryStage = primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
