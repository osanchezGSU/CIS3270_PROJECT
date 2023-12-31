package GUI;
 
import Database.UserDBTEST;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
 
public class User_Forgot_Password {
 
    @FXML
    private ComboBox<String> securityQuestionsComboBox;
 
    @FXML
    private TextField usernameTextField;
 
    @FXML
    private TextField answerTextField;
 
    @FXML
    private Label errorMessageUsername;
 
    @FXML
    private Label errorMessageSecurity;
 
    @FXML
    private Label errorMessageAnswer;
 
    @FXML
    private Label passwordLabel;
 
    @FXML
    private Button retrievePasswordButton;
 
    @FXML
    private Button showPasswordButton;
 
    @FXML
    public void switchToUserLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("User_Login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
 
    @FXML
    public void initialize() {
        // Clear error messages initially
        clearErrorMessages();
 
        // Hide security questions ComboBox, answer text field, and password label initially
        securityQuestionsComboBox.setVisible(false);
        answerTextField.setVisible(false);
        passwordLabel.setVisible(false);
        showPasswordButton.setVisible(false);
    }
 
    @FXML
    public void retrievePasswordButtonOnAction(ActionEvent e) {
        clearErrorMessages();
 
        String username = usernameTextField.getText();
        if (username.isEmpty()) {
            errorMessageUsername.setText("Please enter your username.");
            return;
        }
 
        try {
            UserDBTEST userDB = new UserDBTEST();
            Connection connectDB = userDB.getConnection();
 
            String getUsernameInfo = "SELECT question1, question2 FROM UserSecurityQuestions WHERE username = ?";
            PreparedStatement preparedStatement = connectDB.prepareStatement(getUsernameInfo);
            preparedStatement.setString(1, username);
 
            ResultSet queryResult = preparedStatement.executeQuery();
 
            if (queryResult.next()) {
                String question1 = queryResult.getString("question1");
                String question2 = queryResult.getString("question2");
 
                ObservableList<String> securityQuestions = FXCollections.observableArrayList(question1, question2);
                securityQuestionsComboBox.setItems(securityQuestions);
 
                // Show security questions ComboBox and answer text field
                securityQuestionsComboBox.setVisible(true);
                answerTextField.setVisible(true);
                showPasswordButton.setVisible(true);
            } else {
                errorMessageUsername.setText("The username does not exist.");
            }
 
            connectDB.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
 
   
    @FXML
    public void checkAnswer(ActionEvent e) {
        clearErrorMessages();
 
        String selectedQuestion = securityQuestionsComboBox.getValue();
        String enteredAnswer = answerTextField.getText();
 
        if (selectedQuestion == null || selectedQuestion.isEmpty() || enteredAnswer.isEmpty()) {
            errorMessageUsername.setText("Please select a security question and enter the answer.");
            return;
        }
 
        try {
            UserDBTEST userDB = new UserDBTEST();
            Connection connectDB = userDB.getConnection();
 
            String getUsernameInfo = "SELECT question1, answer1, question2, answer2 FROM UserSecurityQuestions WHERE username = ?";
            PreparedStatement preparedStatement = connectDB.prepareStatement(getUsernameInfo);
            preparedStatement.setString(1, usernameTextField.getText());
 
            ResultSet queryResult = preparedStatement.executeQuery();
 
            if (queryResult.next()) {
                String storedQuestion1 = queryResult.getString("question1").trim();
                String storedAnswer1 = queryResult.getString("answer1").trim();
                String storedQuestion2 = queryResult.getString("question2").trim();
                String storedAnswer2 = queryResult.getString("answer2").trim();
 
                boolean isCorrect = ((selectedQuestion.equals(storedQuestion1) && enteredAnswer.equalsIgnoreCase(storedAnswer1))
                                    || (selectedQuestion.equals(storedQuestion2) && enteredAnswer.equalsIgnoreCase(storedAnswer2)));
 
                if (isCorrect) {
                    displayPassword();
                } else {
                    errorMessageUsername.setText("The answer is incorrect.");
                }
            }
 
            connectDB.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
    @FXML
    public void showPassword(ActionEvent e) {
        clearErrorMessages();
 
        displayPassword();
    }
 
    private void displayPassword() {
        String selectedQuestion = securityQuestionsComboBox.getValue();
        String enteredAnswer = answerTextField.getText();
 
        if (selectedQuestion == null || selectedQuestion.isEmpty() || enteredAnswer.isEmpty()) {
            errorMessageUsername.setText("Please select a security question and enter the answer.");
            return;
        }
 
        try {
            UserDBTEST userDB = new UserDBTEST();
            Connection connectDB = userDB.getConnection();
 
            String getSecurityInfo = "SELECT U.Password, USQ.question1, USQ.answer1, USQ.question2, USQ.answer2 "
                + "FROM Users U "
                + "INNER JOIN UserSecurityQuestions USQ ON U.Username = USQ.username "
                + "WHERE U.Username = ?";
            PreparedStatement preparedStatement = connectDB.prepareStatement(getSecurityInfo);
            preparedStatement.setString(1, usernameTextField.getText());
 
            ResultSet queryResult = preparedStatement.executeQuery();
 
            if (queryResult.next()) {
                String retrievedPassword = queryResult.getString("Password");
                String storedQuestion1 = queryResult.getString("question1").trim();
                String storedAnswer1 = queryResult.getString("answer1").trim();
                String storedQuestion2 = queryResult.getString("question2").trim();
                String storedAnswer2 = queryResult.getString("answer2").trim();
 
                boolean isCorrect = ((selectedQuestion.equals(storedQuestion1) && enteredAnswer.equalsIgnoreCase(storedAnswer1))
                                    || (selectedQuestion.equals(storedQuestion2) && enteredAnswer.equalsIgnoreCase(storedAnswer2)));
 
                if (isCorrect) {
                    passwordLabel.setText("Your password is: " + retrievedPassword);
                    passwordLabel.setVisible(true);
                    clearErrorMessages(); // Clear any error messages
                } else {
                    errorMessageUsername.setText("The answer is incorrect.");
                }
            }
 
            connectDB.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
 
 
 
    private void clearErrorMessages() {
        errorMessageUsername.setText("");
       
    }
}