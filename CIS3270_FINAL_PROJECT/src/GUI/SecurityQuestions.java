package GUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import Database.UserDBTEST;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SecurityQuestions implements Initializable {

	
	@FXML
	private ComboBox<String> question1;
	
	@FXML
	private ComboBox<String> question2;
	
	@FXML
	private TextField answer1;

	@FXML
	private TextField answer2;

	@FXML
    private Button submitButton;
	
	@FXML
	private Text errorMessage;
	 
	private String username;

	public void setUsername(String username) {
		this.username = username;
	}

	
	private String[] questions = {
			"In what city were you born?",
			"What is your mother's maiden name?",
			"What was the name of your elementary school?",
			"What is the name of your first pet?",
			"What year did you graduate High School?"
	};

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		question1.getItems().addAll(questions);
		question2.getItems().addAll(questions);
		 
		// Add listeners to handle changes in ComboBox values
		question1.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				removeSelectedQuestion(newValue);
				updateOtherComboBoxes();	     
			}
		});      
	    
	}

	private void removeSelectedQuestion(String selectedQuestion) {
		int indexToRemove = -1;
		for (int question = 0; question < questions.length; question++) {
			if (questions[question].equals(selectedQuestion)) {
				indexToRemove = question;
				break;
			}
		}

		// If the selected question is found, remove it from the array
		if (indexToRemove != -1) {
			questions[indexToRemove] = null;
		}
	}

	private void updateOtherComboBoxes() {
		String selectedQuestion1 = question1.getValue();
		String selectedQuestion2 = question2.getValue();

		List<String> excludedQuestions = new ArrayList<>();
		if (selectedQuestion1 != null) {
			excludedQuestions.add(selectedQuestion1);
		}
		if (selectedQuestion2 != null) {
			excludedQuestions.add(selectedQuestion2);
		}

		// Create a new ObservableList from the updated questions array excluding selected questions
		question2.setItems(createObservableListFromQuestions(excludedQuestions));
	}

	private ObservableList<String> createObservableListFromQuestions(List<String> exclusions) {
		List<String> nonNullQuestions = new ArrayList<>();

		for (String question : questions) {
			if (question != null && !exclusions.contains(question)) {
				nonNullQuestions.add(question);
			}
		}

		return FXCollections.observableArrayList(nonNullQuestions);
	}
	public void switchToUserHome() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("User_Home.fxml"));
		Stage stage = (Stage) answer1.getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void registerButtonAction(Event event) throws Exception{
		registerUserQA();
		switchToUserHome();
	
	}

	@FXML
	public void getQuestions(ActionEvent event) {
		String userQuestion1 = question1.getValue();
		String userQuestion2 = question2.getValue();
			
	}
	public void getAnswers(ActionEvent event) {
		String userAnswer1 = answer1.getText();
		String userAnswer2 = answer2.getText();
		
	
	}
	
	
	public void registerUserQA() {
        UserDBTEST connectNow = new UserDBTEST();
        Connection connectDB = connectNow.getConnection();
        
       

        
        String insertToRegister = "INSERT INTO UserSecurityQuestions (username, question1, question2, answer1, answer2) VALUES (?, ?, ?, ?, ?)";
        
    	String userQuestion1 = question1.getValue();
    	String userQuestion2 = question2.getValue();
    	
    	
    	String userAnswer1 = answer1.getText();
    	String userAnswer2 = answer2.getText();
   
    	

        try {
        
        	
            PreparedStatement preparedStatement = connectDB.prepareStatement(insertToRegister);
            
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, userQuestion1);
            preparedStatement.setString(3, userQuestion2);
           
            preparedStatement.setString(4, userAnswer1);
            preparedStatement.setString(5, userAnswer2);
           
     

            preparedStatement.executeUpdate();

            
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

	

	
}
