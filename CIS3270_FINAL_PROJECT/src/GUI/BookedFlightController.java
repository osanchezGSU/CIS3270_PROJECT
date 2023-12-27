package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;

import Common.Customer;
import Common.Flight;
import Database.FlightLogDB;
import Database.UserDBTEST;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BookedFlightController {
	  @FXML
	  private Label AirlineName;

	  @FXML
	  private Label ArrivingCity;
	  
	  @FXML
	  private Button DeleteButton;
	  @FXML
	  private Button RemoveButton;

	  @FXML
	  private Label Date;
	    
	  @FXML
	  private Label FlightID;
	  

	  @FXML
	  private Label DepartingCity;

	  @FXML
	  private VBox FlightsLayout;

	  @FXML
	  private Label Time;
	  @FXML
	  private Label Price;
	  
	  @FXML
	  private Label ReservationID;
	  
	  @FXML
	  private Button selectFlights;
	  
	  @FXML
	  private AnchorPane FlightPane;
	  	
	  private PopOver detailPopOver;
	  
	    private PopOver detailsPopOver;
	  
	  private Customer flight;
	  
	  private Integer reservationID;
	  private Integer numOfTravelers;
	  private String reservationDate;
	  
	  private Stage stage;
	  private Scene scene;
	  private Parent root;
	    
	  Customer user = new Customer();
	  
	  public void setData(Customer flight) {
		  
	    	
	  
	    	Time.setText(flight.getBookedFlightTime());
	    	FlightID.setText(flight.getBookedFlightId());
	    	AirlineName.setText(flight.getBookedAirlineName());
	    	ArrivingCity.setText(flight.getBookedArrivalCity());
	    	DepartingCity.setText(flight.getBookedDepartureCity());
	    	Date.setText(flight.getBookedFlightDate());
	
	        reservationID = flight.getBookedReservationID();
	        reservationDate = flight.getReservationDate();
	        numOfTravelers = flight.getNumOfTravelers();
	
	    	
	    	//System.out.println(reservationID);
	    	this.flight = flight;
	    }


	  

	    public void popUpDetails(ActionEvent event) {
	        // Use detailsPopOver directly without re-initializing it
	        detailsPopOver = new PopOver();
	        detailsPopOver.setDetachable(false); 
	        detailsPopOver.setDetached(false);
	        if (detailsPopOver != null && flight != null) {
	            detailsPopOver.setContentNode(createDetailsContent(flight));
	            detailsPopOver.show(selectFlights);
	        } else {
	            System.out.println("detailsPopOver or flight is null");
	        }
	    }
	    	
	    

	    public void initialize(URL location, ResourceBundle resources) {
	    
	        
	    }

	    private Node createDetailsContent(Customer user) {
	    	
	    
	    	
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("BookedFlightDetails.fxml"));
	            VBox popUpDetails = loader.load();

	            // Access labels within the PopOver content and set their values
	            
	            Label flightIdLabel = (Label) popUpDetails.lookup("#SelectedFlightID");
	            if (flightIdLabel != null) {
	                flightIdLabel.setText(user.getBookedFlightId());
	            }

	            Label airlineLabel = (Label) popUpDetails.lookup("#AirlineName");
	            if (airlineLabel != null) {
	                airlineLabel.setText(user.getBookedAirlineName());
	            }
	            Label departureCityLabel = (Label) popUpDetails.lookup("#DepartureCity");
	            if (departureCityLabel != null) {
	            	departureCityLabel.setText(user.getBookedDepartureCity());
	            }
	            Label arrivalCityLabel = (Label) popUpDetails.lookup("#ArrivalCity");
	            if (arrivalCityLabel != null) {
	            	arrivalCityLabel.setText(user.getBookedArrivalCity());
	               
	            }
	            
	  
	            Label flightTimeLabel = (Label) popUpDetails.lookup("#Time");
	            if (flightTimeLabel != null) {
	            	flightTimeLabel.setText(user.getBookedFlightTime());
	            	
	            }
	            
	            Label flightDateLabel = (Label) popUpDetails.lookup("#FlightDate");
	            if (flightDateLabel != null) {
	            	flightDateLabel.setText(user.getBookedFlightDate());

	            }
	           
	            Label reservationDateLabel = (Label) popUpDetails.lookup("#ReservationDate");
	            if (reservationDateLabel != null) {
	            	reservationDateLabel.setText("Reservation Date: "+reservationDate);

	            }
	            Label reservationIDLabel = (Label) popUpDetails.lookup("#ReservationID");
	            if (reservationIDLabel != null) {
	            	reservationIDLabel.setText(String.valueOf(reservationID));

	            }
	  
	            
	            

	            return popUpDetails;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return new Label("Error loading PopUpDetails.fxml");
	        }
	        
	    }
	 
	   
	    
	    @FXML
	   public void removeFlight(ActionEvent event) {
	        removeBookedFlight();
	        
	        Image img = new Image(getClass().getResourceAsStream("Confirmation.png"));
	        
	        
	    	Notifications notification = Notifications.create()
	    		
	    		.title("Remove Flight Confirmation")
	    		.text("Flight Removed Succesfully")
	    		.graphic(new ImageView (img))
	    		.hideAfter(Duration.seconds(6))
	    		.position(Pos.CENTER);
	    	notification.darkStyle();
	    	notification.show();
	    	
	        
	    }
	    public void removeBookedFlight(){
	    	UserDBTEST connectNow = new UserDBTEST();
	        Connection connectDB = connectNow.getConnection();

	        String sql = "DELETE FROM Reservations WHERE ReservationID = ?";

	        try {
	            try (PreparedStatement statement = connectDB.prepareStatement(sql)) {
	                statement.setInt(1, Integer.parseInt(ReservationID.getText()));
	                statement.executeUpdate();
	                System.out.println("Reservation deleted successfully.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (connectDB != null) {
	                    connectDB.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        }

	    
}
	  
	  


