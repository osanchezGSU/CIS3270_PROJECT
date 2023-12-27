package GUI;

import Database.FlightLogDB;
import Database.UserDBTEST;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Common.Customer;
import Common.Flight;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyBookingPage implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private VBox FlightsLayout;

    @FXML
    private Button HomeButton;

    @FXML
    private Button LogButton;

    @FXML
    private Button MyAccountButton;

    @FXML
    private Label from;

    Customer user = new Customer();
    
    Customer userName = User_Registration.user;
    
    public void switchToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("User_Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("User_Home.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToMyBookings(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MyBookingPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FlightsLayout.getChildren().clear();
         
        List<Customer> flights = new ArrayList<>(flights());
        
        for (int i = 0; i < flights.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("BookedFlights.fxml"));

            try {
                AnchorPane anchorPane = fxmlLoader.load();
                BookedFlightController flightItemController = fxmlLoader.getController();
                flightItemController.setData(flights.get(i));
                
                FlightsLayout.getChildren().add(anchorPane);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static List<String> getBookedFlightsForUser(String userName) {
        Customer user = User_Registration.user;
        Flight flight = new Flight();
        List<String> flightInfo = FlightLogDB.getBookedFlightIDs(userName);
        return flightInfo;
    }
    
    private List<Customer> flights() {
        Customer user = User_Registration.user;
        List<Customer> flightInfo = FlightLogDB.getBookedFlightDetails(getBookedFlightsForUser(user.getUsername()));
        List<Customer> flights = new ArrayList<>();
        
     


        for (Customer info : flightInfo) {
            Customer newFlight = new Customer(); // Create a new Customer object for each iteration

            newFlight.setBookedAirlineName(info.getBookedAirlineName());
            newFlight.setBookedFlightId(info.getBookedFlightId());
            newFlight.setBookedFlightTime(info.getBookedFlightTime());
            newFlight.setBookedArrivalCity(info.getBookedArrivalCity());
            newFlight.setBookedDepartureCity(info.getBookedDepartureCity());
            newFlight.setBookedFlightDate(info.getBookedFlightDate());
            newFlight.setBookedPrice(info.getBookedPrice());
         
        
        List<Customer> additionalInfo = FlightLogDB.getAdditionalInfo(user.getUsername());
        System.out.println("Additional Info Size: " + additionalInfo.size());
        for (Customer additionalInfoItem : additionalInfo) {
            
        	newFlight.setNumOfTravelers(additionalInfoItem.getNumOfTravelers());
        	newFlight.setReservationDate(additionalInfoItem.getReservationDate());
        	newFlight.setBookedReservationID(additionalInfoItem.getBookedReservationID());
        	System.out.print(additionalInfoItem.getBookedReservationID());
        	
            
        }

        flights.add(newFlight);
    }
    
        return flights;
    }
    
}
    


