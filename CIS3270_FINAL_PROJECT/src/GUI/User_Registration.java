package GUI;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class User_Registration {
	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	
	 @FXML
	 private PasswordField passwordUserInput;
	 
	 @FXML
	 private PasswordField userSSN;
	 
	 @FXML
	 private TextField usernameInput;
	 
	 @FXML
	 private TextField firstNameUser;
	 
	 @FXML
	 private TextField lastNameUser;
	 
	 @FXML
	 private TextField userEmail;
	 
	 @FXML
	 private TextField userStreetAddress;
	 
	 @FXML
	 private TextField userZipCode;
	 
	 @FXML
	 private TextField userState;

	 @FXML
	 private TextField errorMessage;

	 @FXML
	 private Button submitButton;


}
