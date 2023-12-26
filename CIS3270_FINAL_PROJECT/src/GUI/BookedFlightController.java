package GUI;

import Common.Customer;
import Common.Flight;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

public class BookedFlightController {
	  @FXML
	  private Label AirlineName;

	  @FXML
	  private Label ArrivingCity;
	  
	  @FXML
	  private Button DeleteButton;

	  @FXML
	  private Label Date;
	    
	  @FXML
	  private Label FlightID;
	  

	  @FXML
	  private Label DepartingCity;

	  @FXML
	  private HBox FlightLayout;

	  @FXML
	  private Label Time;
	  
	    private Customer flight;
	  
	  Customer user = new Customer();
	  
	  public void setData(Customer flight) {
		  
	    	
	  
	    	Time.setText(flight.getBookedFlightTime());
	    	FlightID.setText(flight.getBookedFlightId());
	    	AirlineName.setText(flight.getBookedAirlineName());
	    	ArrivingCity.setText(flight.getBookedArrivalCity());
	    	ArrivingCity.setText(flight.getBookedDepartureCity());
	    	Date.setText(flight.getBookedFlightDate());
	    	this.flight = flight;
	    }

}
