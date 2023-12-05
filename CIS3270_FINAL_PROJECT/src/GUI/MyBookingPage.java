package GUI;
 
import Database.UserDBTEST;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
 
public class MyBookingPage {
	
/*
    @FXML
    private Label flightIdLabel;
 
    @FXML
    private Label bookingTimeLabel;
 
    @FXML
    private Label userIdLabel;
 
    public void initialize() {
        try {
            // Establish a database connection
            UserDBTEST connectNow = new UserDBTEST();
            Connection connectDB = connectNow.getConnection();
            // SQL query to retrieve booking information from the Bookings table
            String getBookingInfo = "SELECT * FROM Bookings";
 
            PreparedStatement preparedStatement = connectDB.prepareStatement(getBookingInfo);
            ResultSet queryResult = preparedStatement.executeQuery();
 
            if (queryResult.next()) {
                // Retrieve and display flight id, booking time, and user id
                int flightId = queryResult.getInt("flight_id");
                String bookingTime = queryResult.getString("booking_time");
                int userId = queryResult.getInt("user_id");
 
                flightIdLabel.setText(String.valueOf(flightId));
                bookingTimeLabel.setText(bookingTime);
                userIdLabel.setText(String.valueOf(userId));
            }
 
            connectDB.close(); // Closing database connection
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    */
}