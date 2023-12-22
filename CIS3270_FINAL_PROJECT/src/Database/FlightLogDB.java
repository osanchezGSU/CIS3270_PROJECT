package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    
    public static List<String> getArrivalCities(String departureCity) {
        return getUniqueArrivalCities("ArrivalCity", departureCity);
    }

    public static List<String> getDatesForCities(String departureCity, String arrivalCity) {
        return getUniqueCitiesForCities("FlightDate", departureCity, arrivalCity);
    }

    private static List<String> getUniqueCities(String columnName) {
        List<String> cities = new ArrayList<>();

        String jdbcUrl = "jdbc:mysql://localhost/Aerobookings";
        String username = "root";
        String password = "root";

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

        String jdbcUrl = "jdbc:mysql://localhost/Aerobookings";
        String username = "root";
        String password = "root";

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

        String jdbcUrl = "jdbc:mysql://localhost/Aerobookings";
        String username = "root";
        String password = "root";

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

        String jdbcUrl = "jdbc:mysql://localhost/Aerobookings";
        String username = "root";
        String password = "root";
        
       

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "SELECT *  FROM FlightLog WHERE DepartureCity = ? AND ArrivalCity = ? AND FlightDate = ?;";
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
                        flight.setPrice(resultSet.getString("Price"));
                        ls.add(flight);
                    }
                }
            }
            System.out.println("Number of rows retrieved: " + ls.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ls;
    }
}



