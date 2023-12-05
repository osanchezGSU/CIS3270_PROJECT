package GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class User_Home {
	 	private Stage stage;
	    private Scene scene;
	    private Parent root;


@FXML
private Button MyBookingPage;

private Button LogOutButton;

public void switchToMyBookings(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MyBookingPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
	public void switchToLogin(ActionEvent event) throws IOException {
	        Parent root = FXMLLoader.load(getClass().getResource("User_Login.fxml"));
	        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
	    }
	
	
	
	
}