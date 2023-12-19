package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class FlightLogDB {
	public static List<String> getDepartureCities() {
        return getUniqueCities("DepartureCity");
    }

    public static List<String> getArrivalCities() {
        return getUniqueCities("ArrivalCity");
    }

    public static List<String> getDates() {
        return getUniqueCities("FlightDate");
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
}



