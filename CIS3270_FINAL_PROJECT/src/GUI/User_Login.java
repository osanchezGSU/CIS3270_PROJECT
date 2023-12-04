package GUI;

import java.beans.EventHandler;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


import javax.print.DocFlavor.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXML;

public class User_Login implements Initializable {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private TextField usernameTextField;
	
	@FXML
	private TextField passwordPasswordTextField;
	
	@FXML
	private TextField errorMessage;
	
	@FXML
	private Button submitButton;
	
	
	
	
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
	
	public void loginButtonOnAction(ActionEvent e) {
		
		if (usernameTextField.getText().isBlank()== false && passwordPasswordTextField.getText().isBlank() == false) {
			
			validateLogin();
		}else {
			errorMessage.setText("Please enter your username and password.");
		
	
	}
	}
	
	public void validateLogin() {
		DatabaseConnection connectionNow = new DatabaseConnection();
		Connection connection = connectNow.getConnection();
		
		String verifyLogin = "SELECT count(1) FROM UserAccounts WHERE username = " +usernameTextField.getText() + " AND password = "  + passwordPasswordTextFied.getText() + "'";
				
		try {
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(verifyLogin);
			
			while (queryResult.next()) {
				if(queryResult.getInt(columnindex: 1) == 1) {
					Parent root = FXMLLoader.load(getClass().getResource("User_Home.fxml"));
					stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
					
				}else {
					errorMessage.setText("Invalid Username or Password. Please Try Again.");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}