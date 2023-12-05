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
	 private PasswordField password;
	 
	 @FXML
	 private PasswordField SSN;
	 
	 @FXML
	 private TextField usernameInput;
	 
	 @FXML
	 private TextField firstName;
	 
	 @FXML
	 private TextField lastName;
	 
	 @FXML
	 private TextField email;
	 
	 @FXML
	 private TextField streetAddress;
	 
	 @FXML
	 private TextField zipCode;
	 
	 @FXML
	 private TextField state;

	 @FXML
	 private TextField errorMessage;

	 @FXML
	 private Button submitButton;


}
