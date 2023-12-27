package GUI;

import java.sql.Statement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;

import Common.Customer;
import Common.Flight;
import Database.UserDBTEST;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FlightItem implements Initializable{
	

	@FXML
    private Label Airline;

    @FXML
    private Label FlightID;

    @FXML
    private Label FlightTime;
    
    @FXML
    private Label FlightDate;
    
    @FXML
    private Label FlightTime1;
    
    
    @FXML
    private Label Price;

    @FXML
    private ImageView airlineIMG1;

    @FXML
    private Label travel;

    @FXML
    private Button selectFlights;
    
    @FXML
    private Button CheckOutButton;
    
    @FXML
    private VBox SelectedFlightLayOut;
    
    @FXML
    private Label ArrivalCity;
    
    @FXML
    private Label SelectedFlightID;
    

    @FXML
    private Label NumOfTravelers;
    
    private Integer openSeats;
    
    private PopOver detailsPopOver;
    
    private Flight flight;
    
    private static Integer numberOfTravelers;
    
    private static String formattedDateTime;
    
    private static String arrivalCity;
    
    private static String departureCity;
    
    private static String date;
    
    public int lastInsertedID;
    
    private PopOver detailPopOver;
    
    private Integer newOpenSeats;
    
    Customer user = new Customer();
    
    
    public static void setDate(String date) {
        FlightItem.date = date;
    }

    public static void setArrivalCity(String arrivalCity) {
        FlightItem.arrivalCity = arrivalCity;
    }
    
    public static void setDepartureCity(String departureCity) {
        FlightItem.departureCity = departureCity;
    }
 
    
    
    public static void setNumberOfTravelers(Integer numberOfTravelers ) {
    	FlightItem.numberOfTravelers = numberOfTravelers;
    	
    }
    
    
    public Integer getNumberOfTravelers( ) {
    	return numberOfTravelers;
    	
    }
	public Integer getNewOpenSeats() {
		return newOpenSeats;
	}

	public void setNewOpenSeats(Integer newOpenSeats) {
		this.newOpenSeats = newOpenSeats;
	}
    
 
    
  
    


    
    public void setData(Flight flight) {
    	
    	
    	switch (flight.getAirlineName()) {
        
        case "Spirit": flight.setImgSrc("Spirit.png");
        break;
        case "American Airlines": flight.setImgSrc("AmericanAirline.png");
        break;
        case "Frontier Airlines": flight.setImgSrc("Frontier.png");
        break;
        case "Delta Airlines": flight.setImgSrc("Delta.png");
        break;
        case "JetBlue": flight.setImgSrc("JetBlue.png");
        break;
        case "SouthWest": flight.setImgSrc("SouthWest.png");
        break;
        }
    	
    	Image imgAirline = new Image(getClass().getResourceAsStream(flight.getImgSrc()));
    
    	airlineIMG1.setImage(imgAirline);
    	FlightTime.setText(flight.getTime());
    	FlightID.setText(flight.getFlightID());
    	Price.setText("$" +Integer.toString(flight.getPrice()));
    	Airline.setText(flight.getAirlineName());
    	travel.setText(flight.getTravel());
    	openSeats = flight.getOpenSeats();
    	
    	
    	   	
    	this.flight = flight;
    }

   

    public FlightItem() {
    
        detailsPopOver = new PopOver();
        detailsPopOver.setDetachable(false); 
        detailsPopOver.setDetached(false);
    }

  

    public void popUpDetails(ActionEvent event) {
        // Use detailsPopOver directly without re-initializing it
        if (detailsPopOver != null && flight != null) {
            detailsPopOver.setContentNode(createDetailsContent(flight));
            detailsPopOver.show(selectFlights);
        } else {
            System.out.println("detailsPopOver or flight is null");
        }
    }
    	
    

    public void initialize(URL location, ResourceBundle resources) {
    
        
    }

    private Node createDetailsContent(Flight flight) {
    	
    
    	
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PopUpDetails.fxml"));
            VBox popUpDetails = loader.load();

            // Access labels within the PopOver content and set their values
            
            Label flightIdLabel = (Label) popUpDetails.lookup("#SelectedFlightID");
            if (flightIdLabel != null) {
                flightIdLabel.setText(flight.getFlightID());
            }

            Label airlineLabel = (Label) popUpDetails.lookup("#AirlineName");
            if (airlineLabel != null) {
                airlineLabel.setText(flight.getAirlineName());
            }
            Label departureCityLabel = (Label) popUpDetails.lookup("#DepartureCity");
            if (departureCityLabel != null) {
                departureCityLabel.setText(departureCity);
            }
            Label arrivalCityLabel = (Label) popUpDetails.lookup("#ArrivalCity");
            if (arrivalCityLabel != null) {
                arrivalCityLabel.setText(arrivalCity);
               
            }
            
           Label pricePerTravelerLabel = (Label) popUpDetails.lookup("#PricePerTraveler");
            if ( pricePerTravelerLabel!= null) {
                pricePerTravelerLabel.setText("$" +flight.getPrice());
            }
            
            Label numOfTravelersLabel = (Label) popUpDetails.lookup("#NumOfTravelers");
            if (numOfTravelersLabel != null) {
            	numOfTravelersLabel.setText(String.valueOf("Number Of Travelers: " +numberOfTravelers));
              
                
            } 
            Label flightTimeLabel = (Label) popUpDetails.lookup("#FlightTime1");
            if (flightTimeLabel != null) {
            	flightTimeLabel.setText(flight.getTime());
            	
            }
            
            Label flightDateLabel = (Label) popUpDetails.lookup("#FlightDate");
            if (flightDateLabel != null) {
            	flightDateLabel.setText(date);
         
            	
            }
            Label priceSubtotalLabel = (Label) popUpDetails.lookup("#PriceSubtotal");
            if (priceSubtotalLabel != null) {
            	int priceSubtotal = numberOfTravelers * flight.getPrice();
            	priceSubtotalLabel.setText("$" +priceSubtotal);
         
            	
            }
            Label taxesLabel = (Label) popUpDetails.lookup("#Taxes");
            if (taxesLabel != null) {
            	int priceSubtotal = numberOfTravelers * flight.getPrice();
            	double taxes = (priceSubtotal * 0.075) + priceSubtotal + 5.60 + 4.00;
            	taxesLabel.setText("$" +taxes);
            }
            Label totalLabel = (Label) popUpDetails.lookup("#TotalPrice");
            if (totalLabel != null) {
            	int priceSubtotal = numberOfTravelers * flight.getPrice();
            	double taxes = (priceSubtotal * 0.075) + priceSubtotal + 5.60 + 4.00;
            	double total = priceSubtotal + taxes;
            	totalLabel.setText("$" +total);
            }
           
         
         setNewOpenSeats(openSeats);
            

            return popUpDetails;
        } catch (IOException e) {
            e.printStackTrace();
            return new Label("Error loading PopUpDetails.fxml");
        }
        
    }
    public static String setTimeStamp() {
      
        LocalDateTime currentDateTime = LocalDateTime.now();
 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        formattedDateTime = currentDateTime.format(formatter);
        
        return formattedDateTime;


    }
   
    
    public void bookFlight(ActionEvent event) {
    	try {
    	    detailsPopOver.hide();
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}
    	if (isFlightAlreadyBooked()) {
    		
        	Notifications notification = Notifications.create()
        		
        		.title("Error")
        		.text("Flight is already Booked")
        		.hideAfter(Duration.seconds(6))
        		.position(Pos.CENTER);
        	notification.darkStyle();
        	notification.showError();
           
            return; // Do not proceed further
    	}
    	/*if (isFlightOverBooked()) {
    		Notifications notification = Notifications.create()
            		
            		.title("Error")
            		.text("Flight is Over Booked")
            		.hideAfter(Duration.seconds(6))
            		.position(Pos.CENTER);
            	notification.darkStyle();
            	notification.showError();
    		return;
    	}*/
    	
    	registerBookedFlight();
   
    
    	Image img = new Image(getClass().getResourceAsStream("Confirmation.png"));
    
   
    	Notifications notification = Notifications.create()
    		
    		.title("Booking Confirmation")
    		.text("Confimration Number: "+lastInsertedID)
    		.graphic(new ImageView (img))
    		.hideAfter(Duration.seconds(6))
    		.position(Pos.CENTER);
    	notification.darkStyle();
    	notification.show();
    
    	
    	CheckOutButton.setDisable(true);
    

    
    
}
  
    
    
   
    
    public void registerBookedFlight(){
    	Customer user = User_Registration.user;
    	
    	 setTimeStamp();
    	 
    
    	 UserDBTEST connectNow = new UserDBTEST();
    	 Connection connectDB = connectNow.getConnection();

	        
	        
	       

    	 String insertToRegister = "INSERT INTO Reservations (Username, FlightID, NumOfTravelers, ReservationDate, FlightDateTime) VALUES (?, ?, ?, ?, ?)";

    	 try {
    	        // Use Statement.RETURN_GENERATED_KEYS to get the generated keys
    	        PreparedStatement preparedStatement = connectDB.prepareStatement(insertToRegister, Statement.RETURN_GENERATED_KEYS);
    	        preparedStatement.setString(1, user.getUsername());
    	        preparedStatement.setString(2, SelectedFlightID.getText());
    	        preparedStatement.setInt(3, numberOfTravelers);
    	        preparedStatement.setString(4, setTimeStamp());
    	        preparedStatement.setString(5, FlightDate.getText() +" "+ FlightTime1.getText() );

    	        preparedStatement.executeUpdate();

    	        // Retrieve the generated keys
    	        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
    	        if (generatedKeys.next()) {
    	            // Retrieve the last inserted ID
    	            lastInsertedID = generatedKeys.getInt(1);
    	            //System.out.println("Last Inserted ID: " + lastInsertedID);
    	        }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	}
    private boolean isFlightAlreadyBooked() {
    	 Customer user = User_Registration.user;
    	    String flightIdToCheck = SelectedFlightID.getText();
    	    String flightDateTime =  FlightDate.getText() +" "+ FlightTime1.getText();

    	    // Your database logic to check if the flight ID and time are already in the reservation table
    	    // You can use the UserDBTEST or another appropriate class to execute a SQL query

    	    // Example query (you need to adapt this to your database structure)
    	    String query = "SELECT COUNT(*) FROM Reservations WHERE FlightID = ? AND FlightDateTime = ? AND Username = ?";

    	    try (Connection connection = new UserDBTEST().getConnection();
    	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

    	        preparedStatement.setString(1, flightIdToCheck);
    	        preparedStatement.setString(2, flightDateTime);
    	        preparedStatement.setString(3, user.getUsername());

    	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
    	            if (resultSet.next()) {
    	                int count = resultSet.getInt(1);
    	                return count > 0; // Return true if count is greater than 0 (flight is already booked)
    	            }
    	        }

    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }

    	    return false; // Return false if there is an error or the flight is not booked
    	}
    private boolean isFlightOverBooked() {
    	if (numberOfTravelers > getNewOpenSeats()) {
    		return true;
    	}
    	else return false;
    }
    }
    

    