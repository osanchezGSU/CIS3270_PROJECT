package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Common.Customer;
import Common.Flight;


public class FlightLogDB {
	

	public static List<Flight> getFlightAvailable(String departureCity, String arrivalCity, String date) {
		return getFlightInfo(departureCity, arrivalCity, date);
        
    }
	
	public static List<String> getDepartureCities() {
        return getUniqueCities("DepartureCity");
    }

    public static List<String> getDates() {
        return getUniqueCities("FlightDate");
    }
    public static List<String> getAirlineNames() {
        return getUniqueCities("AirlineName");
    }
    
    public static List<String> getArrivalCities(String departureCity) {
        return getUniqueArrivalCities("ArrivalCity", departureCity);
    }
   

    public static List<String> getDatesForCities(String departureCity, String arrivalCity) {
        return getUniqueCitiesForCities("FlightDate", departureCity, arrivalCity);
    }

    private static List<String> getUniqueCities(String columnName) {
        List<String> cities = new ArrayList<>();

        String databaseName = "AeroBookings";
		//String databaseUser = "root";//
		//String databasePassword = "root";//
		//String url = "jdbc:mysql://localhost/" + databaseName;//
		String jdbcUrl = "jdbc:mysql://database-1.cvnfcprgmyxr.us-east-2.rds.amazonaws.com:3306/" + databaseName;
		String username = "admin";
		String password = "adminadmin";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "SELECT DISTINCT " + columnName + " FROM FlightLog";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String city = resultSet.getString(columnName);
                        cities.add(city);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cities;
    }

    private static List<String> getUniqueCitiesForCities(String columnName, String departureCity, String arrivalCity) {
        List<String> cities = new ArrayList<>();
        String databaseName = "AeroBookings";
		//String databaseUser = "root";//
		//String databasePassword = "root";//
		//String url = "jdbc:mysql://localhost/" + databaseName;//
		String jdbcUrl = "jdbc:mysql://database-1.cvnfcprgmyxr.us-east-2.rds.amazonaws.com:3306/" + databaseName;
		String username = "admin";
		String password = "adminadmin";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "SELECT DISTINCT " + columnName + " FROM FlightLog WHERE DepartureCity = ? AND ArrivalCity = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, departureCity);
                statement.setString(2, arrivalCity);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String city = resultSet.getString(columnName);
                        cities.add(city);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cities;
    }
    
    private static List<String> getUniqueArrivalCities(String columnName, String departureCity) {
        List<String> cities = new ArrayList<>();

        String databaseName = "AeroBookings";
		//String databaseUser = "root";//
		//String databasePassword = "root";//
		//String url = "jdbc:mysql://localhost/" + databaseName;//
		String jdbcUrl = "jdbc:mysql://database-1.cvnfcprgmyxr.us-east-2.rds.amazonaws.com:3306/" + databaseName;
		String username = "admin";
		String password = "adminadmin";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "SELECT DISTINCT " + columnName + " FROM FlightLog WHERE DepartureCity = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, departureCity);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String city = resultSet.getString(columnName);
                        cities.add(city);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cities;
    }
    
    
    
    
    public static List<Flight> getFlightInfo(String arrivalCity, String departureCity, String date) {
    	List<Flight> ls = new ArrayList();
    	String databaseName = "AeroBookings";
		//String databaseUser = "root";//
		//String databasePassword = "root";//
		//String url = "jdbc:mysql://localhost/" + databaseName;//
		String jdbcUrl = "jdbc:mysql://database-1.cvnfcprgmyxr.us-east-2.rds.amazonaws.com:3306/" + databaseName;
		String username = "admin";
		String password = "adminadmin";
        
       

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "SELECT *  FROM FlightLog WHERE DepartureCity = ? AND ArrivalCity = ? AND FlightDate = ? ORDER BY PRICE;";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, departureCity);
                statement.setString(2, arrivalCity);
                statement.setString(3, date);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        // Create a new Flight object for each result
                        Flight flight = new Flight();
                        flight.setFlightID(resultSet.getString("FlightID"));
                        flight.setTime(resultSet.getString("FlightTime"));
                        flight.setAirlineName(resultSet.getString("AirlineName"));
                        flight.setPrice(Integer.parseInt(resultSet.getString("Price")));
                        ls.add(flight);
                    }
                }
            }
       
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ls;
    }
    public static List<String> getAirlineFilters(String departureCity, String arrivalCity, String date) {
        List<String> AirlineNames = new ArrayList<>();

        String databaseName = "AeroBookings";
		//String databaseUser = "root";//
		//String databasePassword = "root";//
		//String url = "jdbc:mysql://localhost/" + databaseName;//
		String jdbcUrl = "jdbc:mysql://database-1.cvnfcprgmyxr.us-east-2.rds.amazonaws.com:3306/" + databaseName;
		String username = "admin";
		String password = "adminadmin";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "SELECT DISTINCT AirlineName FROM FlightLog WHERE DepartureCity = ? AND ArrivalCity = ? AND FlightDate = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, departureCity);
                statement.setString(2, arrivalCity);
                statement.setString(3, date);
             
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String airlineName = resultSet.getString("AirlineName");
                        AirlineNames.add(airlineName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return AirlineNames;
    }
    
    public static List<String> getBookedFlightIDs(String usernameID) {
        List<String> FlightIDs = new ArrayList<>();

        String databaseName = "AeroBookings";
		//String databaseUser = "root";//
		//String databasePassword = "root";//
		//String url = "jdbc:mysql://localhost/" + databaseName;//
		String jdbcUrl = "jdbc:mysql://database-1.cvnfcprgmyxr.us-east-2.rds.amazonaws.com:3306/" + databaseName;
		String username = "admin";
		String password = "adminadmin";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "SELECT FlightID  FROM Reservations WHERE Username = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, usernameID);
                
             
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String flightID = resultSet.getString("FlightID");
                        FlightIDs.add(flightID);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return FlightIDs;
    }
    public static List<Customer> getBookedFlightDetails(List<String> flightIDs) {
        List<Customer> flightDetails = new ArrayList<>();

        // Check if the flightIDs list is empty
        if (flightIDs.isEmpty()) {
            return flightDetails; // or throw an exception or handle it as appropriate
        }
        String databaseName = "AeroBookings";
		//String databaseUser = "root";//
		//String databasePassword = "root";//
		//String url = "jdbc:mysql://localhost/" + databaseName;//
		String jdbcUrl = "jdbc:mysql://database-1.cvnfcprgmyxr.us-east-2.rds.amazonaws.com:3306/" + databaseName;
		String username = "admin";
		String password = "adminadmin";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "SELECT * FROM FlightLog WHERE FlightID IN (" +
                         String.join(",", Collections.nCopies(flightIDs.size(), "?")) +
                         ")";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Set parameters for each FlightID
                for (int i = 0; i < flightIDs.size(); i++) {
                    statement.setString(i + 1, flightIDs.get(i));
                }

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Customer user = new Customer();
                        user.setBookedFlightId(resultSet.getString("FlightID"));
                        user.setBookedFlightTime(resultSet.getString("FlightTime"));
                        user.setBookedAirlineName(resultSet.getString("AirlineName"));
                        user.setBookedArrivalCity(resultSet.getString("ArrivalCity"));
                        user.setBookedDepartureCity(resultSet.getString("DepartureCity"));
                        user.setBookedFlightDate(resultSet.getString("FlightDate"));
                        flightDetails.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flightDetails;
    }
  
}



