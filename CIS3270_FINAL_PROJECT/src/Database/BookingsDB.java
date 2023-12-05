package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class BookingsDB {
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/AeroBookings"; // Database name changed to AeroBookings
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static void main(String[] args) {
        // Call a method to retrieve booking data 
        retrieveBookingData();
    }

    public static void retrieveBookingData() {
        try (Connection connection = getConnection()) {
            // Query to retrieve specific data from the Bookings table
            String query = "SELECT booking_id, flight_id, booking_time, user_id FROM Bookings";

            // Create a prepared statement with the query
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the retrieved data
            while (resultSet.next()) {
                // Retrieve data for each booking
                int bookingId = resultSet.getInt("booking_id");
                String flightId = resultSet.getString("flight_id");
                Timestamp bookingTime = resultSet.getTimestamp("booking_time");
                int userId = resultSet.getInt("user_id");

                // Process retrieved data (you can modify this according to your needs)
                System.out.println("Booking ID: " + bookingId +
                        ", Flight ID: " + flightId +
                        ", Booking Time: " + bookingTime.toString() +
                        ", User ID: " + userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connected to the AeroBookings database!"); // Print message upon successful connection
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found: " + e.getMessage());
        }
        
      return connection;
    }
}


