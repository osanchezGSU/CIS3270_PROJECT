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
    private Label Price;

    @FXML
    private ImageView airlineIMG1;

    @FXML
    private Label travel;

    @FXML
    private Button selectFlights;
    
    @FXML
    private VBox SelectedFlightLayOut;
    
    @FXML
    private Label ArrivalCity;
    
    @FXML
    private Label SelectedFlightID;
    

    @FXML
    private Label NumOfTravelers;
    
    private PopOver detailsPopOver;
    
    private Flight flight;
    
    private static Integer numberOfTravelers;
    
    private static String formattedDateTime;
    
    private static String arrivalCity;
    
    private static String departureCity;
    
    private static String date;
    
    public int lastInsertedID;
    
    private PopOver detailPopOver;
    
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
    	   	
    	this.flight = flight;
    }

   

    public FlightItem() {
        // Initialize detailsPopOver in the constructor
        detailsPopOver = new PopOver();
        detailsPopOver.setDetachable(false); // Disable detachable feature
        detailsPopOver.setDetached(false);
    }

    // Other methods...

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
            Label flightTimeLabel = (Label) popUpDetails.lookup("#FlightTime");
            if (flightTimeLabel != null) {
            	flightTimeLabel.setText(flight.getTime());
            	
            }
            
            Label flightDateLabel = (Label) popUpDetails.lookup("#FlightDate");
            if (flightDateLabel != null) {
            	flightDateLabel.setText(date);
         
            	
            }
            ImageView imageSourceImageView = (ImageView) popUpDetails.lookup("#imgSource");
            if (imageSourceImageView != null) {
                Image imgAirline = new Image(getClass().getResourceAsStream(flight.getImgSrc()));
                imageSourceImageView.setImage(imgAirline);
                System.out.print("Image Set Up");
            }
            
            

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
    
    

    
    
}
  
    
    
   
    
    public void registerBookedFlight(){
    	Customer user = User_Registration.user;
    	
    	 setTimeStamp();
    	 
    
    	 UserDBTEST connectNow = new UserDBTEST();
    	 Connection connectDB = connectNow.getConnection();

	        
	        
	       

    	 String insertToRegister = "INSERT INTO Reservations (Username, FlightID, NumOfTravelers, ReservationDate) VALUES (?, ?, ?, ?)";

    	 try {
    	        // Use Statement.RETURN_GENERATED_KEYS to get the generated keys
    	        PreparedStatement preparedStatement = connectDB.prepareStatement(insertToRegister, Statement.RETURN_GENERATED_KEYS);
    	        preparedStatement.setString(1, user.getUsername());
    	        preparedStatement.setString(2, SelectedFlightID.getText());
    	        preparedStatement.setInt(3, numberOfTravelers);
    	        preparedStatement.setString(4, setTimeStamp());

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
    }