package GUI;

import Database.UserDBTEST;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.fxml.FXML;

public class User_Login {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordTextField;

    @FXML
    private Text errorMessage;

    @FXML
    private Button submitButton;

    public void switchToWelcome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToForgotPassword(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("User_Forgot_Password.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void loginButtonOnAction(ActionEvent e) {
        if (usernameTextField.getText().isBlank() == false && passwordPasswordTextField.getText().isBlank() == false) {
            validateLogin();
        } else {
        	
       
        	
            errorMessage.setText("Please enter your username and password");
        }
    }

    public void switchToUserRegistration(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("User_Registration.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    public void validateLogin() {
        UserDBTEST connectNow = new UserDBTEST();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM Users WHERE username = '"
                + usernameTextField.getText() + "' AND password = '" + passwordPasswordTextField.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
            
                    Platform.runLater(() -> {
                        try {
                            switchToUserHome();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                } else {
                   
                    Platform.runLater(() -> {
                        errorMessage.setText("Invalid Username or Password");
                        
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void switchToUserHome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("User_Home.fxml"));
        stage = (Stage) usernameTextField.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}