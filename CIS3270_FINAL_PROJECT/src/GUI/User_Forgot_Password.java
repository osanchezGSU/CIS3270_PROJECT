package GUI;

import Database.UserDBTEST;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader; // Import FXMLLoader
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label; // Import Label
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class User_Forgot_Password {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField ssnTextField;

    @FXML
    private Label passwordTextArea; // Change TextArea to Label

    @FXML
    private TextField errorMessage;

    @FXML
    private Button retrievePasswordButton;

    @FXML
    private PasswordField passwordField;

    public void switchToUserLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("User_Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void retrievePasswordButtonOnAction(ActionEvent e) {
        if (!ssnTextField.getText().isBlank()) {
            retrievePassword();
        } else {
            errorMessage.setText("Please enter your SSN.");
        }
    }

    public void retrievePassword() {
        try {
            UserDBTEST connectNow = new UserDBTEST();
            Connection connectDB = connectNow.getConnection();
            
            String getUserPassword = "SELECT password FROM Users WHERE SSN = ?";

            PreparedStatement preparedStatement = connectDB.prepareStatement(getUserPassword);
            preparedStatement.setString(1, ssnTextField.getText());
            ResultSet queryResult = preparedStatement.executeQuery();

            if (queryResult != null && queryResult.next()) {
                String retrievedPassword = queryResult.getString("password");
                Platform.runLater(() -> {
                    passwordTextArea.setText("Your password: " + retrievedPassword);
                });
            } else {
                Platform.runLater(() -> {
                    errorMessage.setText("No matching SSN found or error in database query.");
                    passwordTextArea.setText(""); // Clear the password area
                });
            }
            
            connectDB.close(); // Closing database connection using Connection object
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void passwordField() {
        passwordField.setText("Some text"); // Ensure passwordField is initialized in your FXML
    }

    @FXML
    public void initialize() {
        // Initialize other components if needed
    }
}








