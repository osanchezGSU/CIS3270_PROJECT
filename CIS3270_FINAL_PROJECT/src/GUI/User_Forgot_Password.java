package GUI;

import java.io.IOException;

import Database.UsersDB;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class User_Forgot_Password {

    private Stage stage; // Declare the Stage globally
   

    public void start(Stage primaryStage) {
        this.stage = primaryStage; // Set the stage globally for later use

        primaryStage.setTitle("WrightFlights ARS Main Menu");

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(20, 20, 20, 20));

        Label LUsername = new Label("Username");
        TextField username = new TextField();
        Button enter1 = new Button("Enter");
        Button main = new Button("Return to login");

        grid.add(LUsername, 0, 0);
        grid.add(username, 1, 0);
        grid.add(enter1, 1, 1);
        grid.add(main, 2, 2);
        GridPane.setHalignment(enter1, HPos.CENTER);

        enter1.setOnAction(a -> {
            Text LSecurityQ = new Text(UsersDB.getUserSecurityQ(username.getText()));
            Label LSecurityA = new Label("Security Question Answer");
            TextField SecurityA = new TextField();
            Button enter2 = new Button("Enter");
            GridPane.setHalignment(enter2, HPos.CENTER);
            grid.add(LSecurityQ, 1, 3);
            grid.add(LSecurityA, 1, 4);
            grid.add(SecurityA, 1, 5);
            grid.add(enter2, 1, 6);

            enter2.setOnAction(b -> {
                if (SecurityA.getText().equalsIgnoreCase(UsersDB.getUserSecurityA(username.getText()))) {
                    Text passwordText = new Text(UsersDB.getUserPW(username.getText()));
                    grid.add(passwordText, 1, 7);

                } else {
                    Alert_Message.display("Password Retrieval Error", "Password Retrieval failed. Please make sure all information is correct and try again.");
                }
            });
        });

        main.setOnAction(c -> {
            try {
                switchToUserHome(); // Call the method to switch to User Home
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        grid.setAlignment(Pos.CENTER);

        Scene scene = new Scene(grid, 700, 300);
        primaryStage.setTitle("Customer Password Recovery");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to switch to User Home
    public void switchToUserHome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("User_Home.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}


