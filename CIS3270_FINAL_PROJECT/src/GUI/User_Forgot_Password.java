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
    private Label passwordTextArea;

    @FXML
    private Button retrievePasswordButton;

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

        // Initialize security questions ComboBox (initially empty)
        securityQuestionsComboBox.setItems(FXCollections.observableArrayList());
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

            String getUsernameInfo = "SELECT userQuestion1, userQuestion2, userAnswer1, userAnswer2, password FROM SecurityQuestions WHERE username = ?";
            PreparedStatement preparedStatement = connectDB.prepareStatement(getUsernameInfo);
            preparedStatement.setString(1, username);

            ResultSet queryResult = preparedStatement.executeQuery();

            if (queryResult.next()) {
                String question1 = queryResult.getString("userQuestion1");
                String question2 = queryResult.getString("userQuestion2");

                ObservableList<String> securityQuestions = FXCollections.observableArrayList(question1, question2);
                securityQuestionsComboBox.setItems(securityQuestions);

                // Show security questions ComboBox and answer text field
                securityQuestionsComboBox.setVisible(true);
                answerTextField.setVisible(true);
            } else {
                errorMessageUsername.setText("The username does not exist.");
            }

            connectDB.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void clearErrorMessages() {
        errorMessageUsername.setText("");
        errorMessageSecurity.setText("");
        errorMessageAnswer.setText("");
    }
}


