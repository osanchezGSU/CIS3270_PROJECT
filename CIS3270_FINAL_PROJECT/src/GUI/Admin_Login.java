package GUI;

import Database.AdminDB;
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

public class Admin_Login {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField adminusernameTextField;

    @FXML
    private PasswordField adminpasswordPasswordTextField;

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

    public void switchToAdminForgotPassword(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Admin_Forgot_Password.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void loginButtonOnAction(ActionEvent e) {
    
        if (adminusernameTextField.getText().isBlank() == false && adminpasswordPasswordTextField.getText().isBlank() == false) {
            validateLogin();
        } else {    	
       
        	
            errorMessage.setText("Please enter your username and password");
        }
    }

    public void switchToAdminRegistration(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Admin_Registration.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    public void validateLogin() {
        AdminDB connectNow = new AdminDB();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM Users WHERE username = '"
                + adminusernameTextField.getText() + "' AND password = '" + adminpasswordPasswordTextField.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
            
                    Platform.runLater(() -> {
                        try {
                            switchToAdminHome();
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

    public void switchToAdminHome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Admin_Home.fxml"));
        stage = (Stage) adminusernameTextField.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}