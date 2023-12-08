package GUI;

import java.util.HashMap;
import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SecurityQuestions implements Initializable {

	
	@FXML
	private ComboBox<String> question1;
	
	@FXML
	private ComboBox<String> question2;

	@FXML
	private ComboBox<String> question3;
	
	@FXML
	private TextField answer1;

	@FXML
	private TextField answer2;

	@FXML
	private TextField answer3;
	
	@FXML
    private Button submitButton;
	
	private String [] questions = {"In what city where you born?",  "What is your mother's maiden name?",
			"What was the name of your elementary school?", "What is the name of your first pet?", "In what city where you born in?"};
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	        question1.getItems().addAll(questions);
	        question2.getItems().addAll(questions);
	        question3.getItems().addAll(questions);
	 
	    }
	public void switchToUserHome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("User_Home.fxml"));
        Stage stage = (Stage) answer1.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
	
	@FXML
	public void getQuestions(ActionEvent event) {
		String userQuestion1 = question1.getValue();
		String userQuestion2 = question2.getValue();
		String userQuestion3 = question3.getValue();			
	}
	public void getAnswers(ActionEvent event) {
		String userAnswer1 = answer1.getText();
		String userAnswer2 = answer2.getText();
		String userAnswer3 = answer3.getText();	
	
		HashMap<String, String> QandA = new HashMap<String, String>();

		QandA.put(question1.getValue(), answer1.getText());
		QandA.put(question2.getValue(), answer2.getText());
		QandA.put(question3.getValue(), answer3.getText());
	}

	
}
