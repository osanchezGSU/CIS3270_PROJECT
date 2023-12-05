package GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;



public class Scene_Controller {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	public void switchToCustomer(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("User_Login.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
				
				
}
	public void switchToAdmin(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("Admin_Login.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	

	public void switchToUserRegistration(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("User_Registration.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void switchToWelcome(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void switchToUserForgotPassword(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("User_Forgot_Password.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void switchToSearchEngine(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Search_Engine.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
}