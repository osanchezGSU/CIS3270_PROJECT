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
import javafx.animation.Animation;


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
	 

	 
	 private String username;
	 
	 private String password;

	 public void setUsername(String username) {
		 this.username = username;
	 }
	 
	 public void setPassword(String password) {
		 this.password = password;
	 }
	 
	  String errorStyle = String.format("-fx-background-color: transparent;\n"
		  		+ "	-fx-background-radius: 10px;\n"
		  		+ "	-fx-border-color: red;\n"
		  		+ "	-fx-border-width: 2px;\n"
		  		+ "	-fx-border-radius: 10px;\n");
	  String defaultStyle = String.format("-fx-background-color: transparent;\n"
	  		+ "	-fx-background-radius: 10px;\n"
	  		+ "	-fx-border-color: #6a8ff6;\n"	
	  		+ "	-fx-border-width: 2px;\n"
	  		+ "	-fx-border-radius: 10px;\n");
	
	  public void switchToSecurityQuestions() throws IOException {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("SecurityQuestion.fxml"));
	        Parent root = loader.load();

	        // Retrieve the controller instance
	        SecurityQuestions securityQuestionsController = loader.getController();

	        // Pass the username parameter
	        securityQuestionsController.setPassword(password);
	        securityQuestionsController.setUsername(username);
	        System.out.println("Switching to SecurityQuestions with username: " + username);
	        // Switch to the scene
	        stage = (Stage) setFirstName.getScene().getWindow();
	        scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
	    }
	 
	  public void switchToWelcome(ActionEvent event) throws IOException {
	        root = FXMLLoader.load(getClass().getResource("User_Login.fxml"));
	        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
	    }
	  
	  public void ClearButton(Event event) {
		  TextField[] textFields = {setFirstName, setLastName, setUsername, setPassword, setEmail, setSSN, setStreetAddress, setZipCode, setState, confirmPassword};
		  for (TextField textField : textFields) {
			  textField.clear();
		  }
		  
	  }
	
	  public void registerButtonAction(Event event) {
		  
		  resetStyle();
		  
		  TextField[] textFields = {setFirstName, setLastName, setUsername, setPassword, setEmail, setSSN, setStreetAddress, setZipCode, setState, confirmPassword};
	
		    boolean hasEmptyField = false;

		    for (TextField textField : textFields) {
		        if (textField.getText().isEmpty()) {
		            textField.setStyle(errorStyle);
		            hasEmptyField = true;
		        }
		    }

		    if (hasEmptyField) {
		        errorMessage.setText("Please fill in all fields.");
		        return;
		    }	   
	        if (isUsernameTaken(setUsername.getText())) {
	        	
	        	setUsername.setStyle(errorStyle);
	            errorMessage.setText("Please choose a different Username.");
	            return;
	        }
	        
	        String ssn = setSSN.getText();

	        if (ssn.length() != 11 || ssn.charAt(3) != '-' || ssn.charAt(6) != '-') {
	            errorMessage.setText("Invalid SSN");
	            return;
	        }

	        for (int i = 0; i < ssn.length(); i++) {
	            if (i == 3 || i == 6) {
	                continue;
	            }

	            if (!Character.isDigit(ssn.charAt(i))) {
	                errorMessage.setText("Invalid SSN");
	                return;
	            }
	        }
		    if (setPassword.getText().equals(confirmPassword.getText())) {
			  Platform.runLater(() -> {
                  try {
                
                	  
                	  registerUser();
                	  
                	  setPassword(setPassword.getText());
                	  
                	  setUsername(setUsername.getText());
         
                      switchToSecurityQuestions();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              });
          } else {
        	  setPassword.setStyle(errorStyle);
        	  confirmPassword.setStyle(errorStyle);
			  errorMessage.setText("Password does not match");
			  
		  }
		    }
	  
	  
	  public void resetStyle() {
		  TextField[] textFields = {setFirstName, setLastName, setUsername, setPassword, setEmail, setSSN, setStreetAddress, setZipCode, setState, confirmPassword};

		  for (TextField textField : textFields) {
			  
		
		        if (textField.getText().isEmpty() != true) {
		            textField.setStyle(defaultStyle);
		           
		        }
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