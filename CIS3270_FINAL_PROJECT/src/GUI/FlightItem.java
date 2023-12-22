package GUI;

import java.net.URL;
import java.util.ResourceBundle;



import Common.Flight;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    private Button viewDetails1;

    
    public void setData(Flight flight) {
    	
    	switch (flight.getAirlineName()) {
        
        case "Spirit": flight.setImgSrc("Spirit.png");
        break;
        case "American Airlines": flight.setImgSrc("AmericanAirlines.png");
        break;
        case "Frontier Airlines": flight.setImgSrc("Frontier.png");
        break;
        case "Delta Airlines": flight.setImgSrc("Delta.png");
        break;
        case "JetBlue": flight.setImgSrc("JetBlue.png");
        break;
        case "SouthWest": flight.setImgSrc("Spirit.png");
        break;
        }
    	System.out.println("Setting data for flight: " + flight);
    	Image imgAirline = new Image(getClass().getResourceAsStream(flight.getImgSrc()));
    	
    	
    	airlineIMG1.setImage(imgAirline);
    	FlightTime.setText(flight.getTime());
    	FlightID.setText(flight.getFlightID());
    	Price.setText(flight.getPrice());
    	Airline.setText(flight.getAirlineName());
    	travel.setText(flight.getTravel());
    		
    }
    
	public void initialize(URL location, ResourceBundle resources) {
		

	}

	
	
	

}
