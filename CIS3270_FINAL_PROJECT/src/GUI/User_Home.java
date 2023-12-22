package GUI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.Collections;

import org.controlsfx.control.SearchableComboBox;

import Common.Flight;
import Database.FlightLogDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//import org.controlsfx.control.textfield.TextFields;
//import org.controlsfx.control.textfield.AutoCompletionBinding;

public class User_Home implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField FromSearchField;
    
    @FXML
    private SearchableComboBox<String> DepartureChoiceBox;
    
    @FXML
    private SearchableComboBox<String> DestinationChoiceBox;
    
    @FXML
    private DatePicker date;
    
    @FXML
    private ChoiceBox<String> DateChoiceBox;
    

    @FXML
    private VBox FlightsLayout;


    @FXML
    private Button LogOutButton;

    @FXML
    private Button bookButton;

    @FXML
    private Button MyBookingButton;

    @FXML
    private Label from;

	Flight flight = new Flight();
    
    FlightLogDB flightLogDB = new FlightLogDB();

    List<String> departureCities = flightLogDB.getDepartureCities();
  //  List<String> arrivalCities = flightLogDB.getArrivalCities();
    List<String> dates = flightLogDB.getDates();

    public void switchToMyBookings(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MyBookingPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("User_Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	Collections.sort(departureCities);
    	DepartureChoiceBox.getItems().addAll(departureCities);
        DepartureChoiceBox.setOnAction(this::getDepartureCities);
        
        
        
    }
    
 
    private List<Flight> flights() {
        LocalDate selectedDate = date.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = selectedDate.format(formatter);

        List<Flight> flights = new ArrayList<>();

        Flight flight = new Flight();
        flight.setDepartureCity(DepartureChoiceBox.getValue());
        flight.setArrivalCity(DestinationChoiceBox.getValue());
        flight.setDateString(formattedDate);
        flight.setTravel(flight.getDepartureCity() + " - " + flight.getArrivalCity());

        List<Flight> flightInfo = FlightLogDB.getFlightInfo(flight.getArrivalCity(), flight.getDepartureCity(), flight.getDateString());

        for (Flight info : flightInfo) {
            Flight newFlight = new Flight(); // Create a new Flight object for each iteration
            
          
            newFlight.setAirlineName(info.getAirlineName());
            newFlight.setFlightID(info.getFlightID());
            newFlight.setTime(info.getTime());
            //newFlight.setImgSrc(info.getImgSrc());
            newFlight.setPrice(info.getPrice());
            newFlight.setTravel(flight.getTravel()); // Set the travel string for each new flight
            flights.add(newFlight);
        }

        return flights;
    }

    @FXML
    public void searchEngine(ActionEvent event) throws IOException {
    	FlightsLayout.getChildren().clear();
 
        List<Flight> flights = new ArrayList<>(flights());
        
        for (int i = 0; i < flights.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Flight_Item.fxml"));

            try {
                AnchorPane anchorPane = fxmlLoader.load();
                FlightItem flightItemController = fxmlLoader.getController();
                flightItemController.setData(flights.get(i));
                FlightsLayout.getChildren().add(anchorPane);
            
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
  
    public void getDepartureCities(ActionEvent event) {
    	Flight flight = new Flight();
        flight.setDepartureCity(DepartureChoiceBox.getValue());
        updateArrivalChoiceBox(flight.getDepartureCity());
        updateFlightObject(flight);
        
        
    } 

    public void getArrivalCities(ActionEvent event) {
            Flight flight = new Flight();
            flight.setArrivalCity(DestinationChoiceBox.getValue());
            updateFlightObject(flight);
            
    }

    @FXML
    public void getDates(ActionEvent event) {
    	Flight flight = new Flight();
        LocalDate selectedDate = date.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = selectedDate.format(formatter);
        System.out.print(formattedDate);
        
        flight.setDateString(formattedDate.toString());
        updateFlightObject(flight);
      
    }
    
    private void updateArrivalChoiceBox(String departureCity) {
    	
        List<String> arrivalCities = flightLogDB.getArrivalCities(departureCity);

            Collections.sort(arrivalCities);
            DestinationChoiceBox.getItems().clear();
            DestinationChoiceBox.getItems().addAll(arrivalCities);
    }
    
    private void updateFlightObject(Flight flight) {
        // Update the Flight object used for searching
        this.flight.setDepartureCity(flight.getDepartureCity());
        this.flight.setArrivalCity(flight.getArrivalCity());
        this.flight.setDateString(flight.getDateString());
    }

/*
    public void searchEngine(ActionEvent event) throws IOException {
        Flight flight = new Flight();
        flight.setDepartureCity(DepartureChoiceBox.getValue());
        flight.setArrivalCity(DestinationChoiceBox.getValue());

        List<Flight> flights = FlightLogDB.getFlightInfo(flight.getArrivalCity(), flight.getDepartureCity(), flight.getDateString());

        for (int i = 0; i < flights.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Flight_Item.fxml"));

            try {
                AnchorPane anchorPane = fxmlLoader.load();
                FlightItem flightItemController = fxmlLoader.getController();
                flightItemController.setData(flights.get(i));
                FlightsLayout.getChildren().add(anchorPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    */

}
