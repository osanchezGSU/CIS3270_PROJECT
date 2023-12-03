package GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;



public class Welcome {
	
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
	
}