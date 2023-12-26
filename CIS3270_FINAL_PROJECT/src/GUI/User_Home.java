package GUI;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import org.controlsfx.control.CheckListView;
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
import javafx.scene.control.ComboBox;
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
    private Button AfternoonButton;
    
    @FXML
    private Button MorningButton;
    @FXML
    private Button EveningButton;
    

    @FXML
    private CheckListView<String> AirlinesCheckList;
    
    @FXML
    private CheckListView<String> TimeCheckList;
    
    @FXML
    private CheckListView<String> PriceCheckList;

    @FXML
    private TextField FromSearchField;
    
    @FXML
    private SearchableComboBox<String> DepartureChoiceBox;
    
    @FXML
    private SearchableComboBox<String> DestinationChoiceBox;
    
    @FXML
    private DatePicker date;
    
    
    @FXML
    private ComboBox <Integer> NumOfTravelers;
    
    @FXML
    private VBox FlightsLayout;


    @FXML
    private Button LogOutButton;

    @FXML
    private Button bookButton;

    @FXML
    private Button filterButton;
    
    @FXML
    private Button clearFiltersButton;
    
    
    @FXML
    private Button filterTimeButton;
    
    @FXML
    private Button MyBookingButton;
    
    @FXML 
    private Button HomeButton;

    @FXML
    private Label from;
    
    @FXML
    private AnchorPane filterControlPane;
    
    
	Flight flight = new Flight();
    
    FlightLogDB flightLogDB = new FlightLogDB();
    
    List<String> AirlineNames = flightLogDB.getAirlineNames();

    List<String> departureCities = flightLogDB.getDepartureCities();
    //List<String> arrivalCities = flightLogDB.getArrivalCities();
    List<String> dates = flightLogDB.getDates();
    
    List<Integer> numOfTravelers = Arrays.asList(1, 2, 3, 4, 5);
    
    
    

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
    public void resetHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("User_Home.fxml"));
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
        
        NumOfTravelers.getItems().addAll(numOfTravelers);
        NumOfTravelers.setOnAction(this::getNumOfTravelers);

        
        clearFiltersButton.setOnAction(this::clearFilters);
     
        
      
    }
    
    
 
    private List<Flight> flights() {
        LocalDate selectedDate = date.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = selectedDate.format(formatter);
        
        

        List<Flight> flights = new ArrayList<>();

        Flight flight = new Flight();
        
        flight.setNumOfTravelers(NumOfTravelers.getSelectionModel().getSelectedItem());
        
        flight.setDepartureCity(DepartureChoiceBox.getValue());
        flight.setArrivalCity(DestinationChoiceBox.getValue());
        flight.setDateString(formattedDate);
        flight.setTravel(flight.getDepartureCity() + " - " + flight.getArrivalCity());
        
        updateAirlineFilterOptions(flight.getDepartureCity(), flight.getArrivalCity(), flight.getDateString());
   
   
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
    
    
    public void getNumOfTravelers(ActionEvent event) {
    	
    	 int selectedNumOfTravelers = NumOfTravelers.getValue();
         flight.setNumOfTravelers(selectedNumOfTravelers);
         updateFlightObject(flight);

    }
    
  
    

  

    @FXML
    public void searchEngine(ActionEvent event) throws IOException {
    	 LocalDate selectedDate = date.getValue();
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         String formattedDate = selectedDate.format(formatter);
         
         FlightItem.setDate(formattedDate.toString());

    	 FlightItem.setArrivalCity(DestinationChoiceBox.getValue());
    	 
    	 FlightItem.setDepartureCity(DepartureChoiceBox.getValue());
    	 
    	 FlightItem.setNumberOfTravelers(NumOfTravelers.getValue());
    	 
    	
    
        filterControlPane.setVisible(true);

        
    	
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
       
        //System.out.println(flight.getNumOfTravelers());
    	
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
        
        flight.setDateString(formattedDate.toString());
        updateFlightObject(flight);
      
    }
    
    private void updateArrivalChoiceBox(String departureCity) {
    	
        List<String> arrivalCities = flightLogDB.getArrivalCities(departureCity);

            Collections.sort(arrivalCities);
            DestinationChoiceBox.getItems().clear();
            DestinationChoiceBox.getItems().addAll(arrivalCities);
    }
    
    private void updateAirlineFilterOptions(String departureCity, String arrivalCity, String date) {
	 
    	List<String> availableAirlines = flightLogDB.getAirlineFilters(departureCity, arrivalCity, date);
     
     
    	Collections.sort(availableAirlines);
    	AirlinesCheckList.getItems().clear();
    	AirlinesCheckList.getItems().addAll(availableAirlines);
    }
    
    private void updateTimeOption(String departureCity, String arrivalCity, String date) {
   	 
    	List<String> availableAirlines = flightLogDB.getAirlineFilters(departureCity, arrivalCity, date);
     
     
    	Collections.sort(availableAirlines);
    	AirlinesCheckList.getItems().clear();
    	AirlinesCheckList.getItems().addAll(availableAirlines);
    }
    
    private void updateFlightObject(Flight flight) {
        // Update the Flight object used for searching
        this.flight.setDepartureCity(flight.getDepartureCity());
        this.flight.setArrivalCity(flight.getArrivalCity());
        this.flight.setDateString(flight.getDateString());
        this.flight.setNumOfTravelers(flight.getNumOfTravelers());
       
    	
    }
    
    @FXML
    public void applyFilters(ActionEvent event) {
        FlightsLayout.getChildren().clear(); // Clear existing flight items

        List<Flight> flights = new ArrayList<>(flights());

        // Get the current checked state of airlines before applying filters
        List<String> previouslyCheckedAirlines = new ArrayList<>(AirlinesCheckList.getCheckModel().getCheckedItems());

        // Filter by selected airlines
        List<String> selectedAirlines = AirlinesCheckList.getCheckModel().getCheckedItems();
        flights = filterFlightsByAirlines(flights, selectedAirlines);

        String timeCategory = flight.getFilteredTime();
        if (timeCategory != null && !timeCategory.isEmpty()) {
            // Filter flights by time
            flights = filterFlightsByTime(flights, timeCategory);
        }

        // Display filtered flights
        displayFlights(flights);

        // Restore the checked state of airlines
        AirlinesCheckList.getCheckModel().checkIndices(getIndices(previouslyCheckedAirlines, AirlinesCheckList.getItems()));
    }
    
    private <T> int[] getIndices(List<T> items, List<T> source) {
        return items.stream()
                .mapToInt(source::indexOf)
                .toArray();
    }
    
    private List<Flight> filterFlightsByAirlines(List<Flight> flights, List<String> selectedAirlines) {
      
    	
    	if (selectedAirlines.isEmpty()) {
            return flights; // No filter applied, return all flights
        }

        List<Flight> filteredFlights = new ArrayList<>();

        for (Flight flight : flights) {
            if (selectedAirlines.contains(flight.getAirlineName())) {
                filteredFlights.add(flight);
            }
        }

        return filteredFlights;
    }
    
    private void displayFlights(List<Flight> flights) {
    	FlightsLayout.getChildren().clear();
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
    private List<Flight> filterFlightsByTime(List<Flight> flights, String timeCategory) {
        // Implement the logic to filter flights by time based on the time category
        // You can adjust this logic based on your specific requirements
    	if (timeCategory == null || timeCategory.isEmpty()) {
            return flights; // No filter applied, return all flights
        }
    	
        List<Flight> filteredFlights = new ArrayList<>();

        for (Flight flight : flights) {
            if (isInTimeCategory(flight.getTime(), timeCategory)) {
                filteredFlights.add(flight);
            }
        }

        return filteredFlights;
    }

    private boolean isInTimeCategory(String flightTime, String timeCategory) {
        // Implement the logic to categorize flights into time categories
        // For simplicity, let's assume the time is in "hh:mm a" format
    	
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        try {
            Date flightDate = sdf.parse(flightTime);
            
            if (timeCategory.equals("Morning") && flightDate.after(sdf.parse("05:00 AM")) && flightDate.before(sdf.parse("11:59 AM"))) {
                return true;
            } else if (timeCategory.equals("Afternoon") && flightDate.after(sdf.parse("12:00 PM")) && flightDate.before(sdf.parse("05:59 PM"))) {
                return true;
            } else if (timeCategory.equals("Evening") && flightDate.after(sdf.parse("06:00 PM")) && flightDate.before(sdf.parse("11:59 PM"))) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void setTimeFilterMorning(ActionEvent event) {
      
      	flight.setFilteredTime("Morning");
      	
      	
    }

    public void setTimeFilterAfternoon(ActionEvent event) {
    
      	flight.setFilteredTime("Afternoon");
      	
    }

    public void setTimeFilterEvening(ActionEvent event) {
     
      	flight.setFilteredTime("Evening");
      
    }
    @FXML
    public void clearFilters(ActionEvent event) {
        // Clear the filters and reset the search engine to the original state

        // Clear checklists
        AirlinesCheckList.getCheckModel().clearChecks();

        // Reset time filter
        flight.setFilteredTime(null);

        // Reset other filter variables as needed

        // Trigger the search engine with the original state
        try {
            searchEngine(event);
        } catch (IOException e) {
            // Handle the IOException if necessary
            e.printStackTrace(); // or log the exception
        }
    }
   }



    
   
    
