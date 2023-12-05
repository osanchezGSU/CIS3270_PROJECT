package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightLogDB {
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/AeroBookings"; // Updated database name to AeroBookings
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static void main(String[] args) {
        // Call a method to retrieve flight log data or perform other tasks
        retrieveFlightLogData();
    }

    public static void retrieveFlightLogData() {
        try (Connection connection = getConnection()) {
            // Query to retrieve specific data from the FlightLog table
            String query = "SELECT flight_id, from_city, depart_time, to_city, arrive_time, total_seats, seats_available, flight_date FROM FlightLog";

            // Create a prepared statement with the query
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the retrieved data
            while (resultSet.next()) {
                // Retrieve data for each flight log entry
                String flightId = resultSet.getString("flight_id");
                String fromCity = resultSet.getString("from_city");
                String departTime = resultSet.getString("depart_time");
                String toCity = resultSet.getString("to_city");
                String arriveTime = resultSet.getString("arrive_time");
                int totalSeats = resultSet.getInt("total_seats");
                int seatsAvailable = resultSet.getInt("seats_available");
                String flightDate = resultSet.getString("flight_date");

                // Process retrieved data 
                System.out.println("Flight ID: " + flightId +
                        ", From City: " + fromCity +
                        ", Departure Time: " + departTime +
                        ", To City: " + toCity +
                        ", Arrival Time: " + arriveTime +
                        ", Total Seats: " + totalSeats +
                        ", Seats Available: " + seatsAvailable +
                        ", Flight Date: " + flightDate);
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



