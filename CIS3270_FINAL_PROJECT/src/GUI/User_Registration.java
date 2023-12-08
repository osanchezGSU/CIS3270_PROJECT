package GUI;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Database.UserDBTEST;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.text.Text;


public class User_Registration {
	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	
	 @FXML
	 private PasswordField setPassword;
	 
	 @FXML
	 private PasswordField confirmPassword;
	 
	 @FXML
	 private PasswordField setSSN;
	 
	 @FXML
	 private TextField setUsername;
	 
	 @FXML
	 private TextField setFirstName;
	 
	 @FXML
	 private TextField setLastName;
	 
	 @FXML
	 private TextField setEmail;
	 
	 @FXML
	 private TextField setStreetAddress;
	 
	 @FXML
	 private TextField setZipCode;
	 
	 @FXML
	 private TextField setState;

	 @FXML
	 private Text errorMessage;
	 

	 @FXML
	 private Button submitButton;
	 
	 public void switchToSecurityQuestions() throws IOException {
	        root = FXMLLoader.load(getClass().getResource("SecurityQuestion.fxml"));
	        stage = (Stage) setFirstName.getScene().getWindow();
	        scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
	    }
	 
	  public void switchToWelcome(ActionEvent event) throws IOException {
	        root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
	        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
	    }
	  
	  public void switchToUserHome() throws IOException {
	        root = FXMLLoader.load(getClass().getResource("User_Home.fxml"));
	        stage = (Stage) setFirstName.getScene().getWindow();
	        scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
	    }
	  
	  public void registerButtonAction(Event event) {
		  if (setPassword.getText().equals(confirmPassword.getText())) {
			  registerUser();
			  Platform.runLater(() -> {
                  try {
                      switchToUserHome();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              });
          } else {
			  errorMessage.setText("Password does not match");
			  
		  }
	  }

	  public void registerUser() {
	        UserDBTEST connectNow = new UserDBTEST();
	        Connection connectDB = connectNow.getConnection();

	        String firstName = setFirstName.getText();
	        String lastName = setLastName.getText();
	        String username = setUsername.getText();
	        String password = setPassword.getText();
	        String email = setEmail.getText();
	        String ssn = setSSN.getText();
	        String address = setStreetAddress.getText();
	        String zipcode = setZipCode.getText();
	        String state = setState.getText();

	        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty() || email.isEmpty() || ssn.isEmpty() || address.isEmpty() || zipcode.isEmpty() || state.isEmpty()) {
	            errorMessage.setText("Please fill in all fields.");
	            return;
	        }

	   
	        if (isUsernameTaken(username)) {
	            errorMessage.setText("Username is already taken. Please choose a different one.");
	            return;
	        }

	        String insertToRegister = "INSERT INTO Users(First_Name, Last_Name, Address, Zip_Code, State, Username, Password, Email, SSN) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	        try {
	            PreparedStatement preparedStatement = connectDB.prepareStatement(insertToRegister);
	            preparedStatement.setString(1, firstName);
	            preparedStatement.setString(2, lastName);
	            preparedStatement.setString(3, address);
	            preparedStatement.setString(4, zipcode);
	            preparedStatement.setString(5, state);
	            preparedStatement.setString(6, username);
	            preparedStatement.setString(7, password);
	            preparedStatement.setString(8, email);
	            preparedStatement.setString(9, ssn);

	            preparedStatement.executeUpdate();

	            Platform.runLater(() -> {
	                try {
	                    switchToUserHome();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            });
	        } catch (SQLException e) {
	            e.printStackTrace();
	
	        }
	    }

	    private boolean isUsernameTaken(String username) {
	        UserDBTEST connectNow = new UserDBTEST();
	        Connection connectDB = connectNow.getConnection();
	        String query = "SELECT * FROM Users WHERE Username = ?";

	        try (PreparedStatement preparedStatement = connectDB.prepareStatement(query)) {
	            preparedStatement.setString(1, username);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            return resultSet.next();
	        } catch (SQLException e) {
	            e.printStackTrace();

	        }

	        return false; 
	    }
	}