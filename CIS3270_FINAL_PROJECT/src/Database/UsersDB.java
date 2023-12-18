package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDB {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/AeroBookings";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static void main(String[] args) {
        retrieveUserData();
    }

    public static void retrieveUserData() {
        try (Connection connection = getConnection()) {

            String query = "SELECT First_Name, Last_Name, Username, Password, Email, SSN, Address, State, Zip_Code FROM Users";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String firstName = resultSet.getString("First_Name");
                String lastName = resultSet.getString("Last_Name");
                String username = resultSet.getString("Username");
                String password = resultSet.getString("Password");
                String email = resultSet.getString("Email");
                String ssn = resultSet.getString("SSN");
                String address = resultSet.getString("Address");
                String state = resultSet.getString("State");
                String zipCode = resultSet.getString("Zip_Code");

                System.out.println("First Name: " + firstName +
                        ", Last Name: " + lastName +
                        ", Username: " + username +
                        ", Password: " + password +
                        ", Email: " + email +
                        ", SSN: " + ssn +
                        ", Address: " + address +
                        ", State: " + state +
                        ", Zip Code: " + zipCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connected to the AeroBookings database!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to connect to the database.");
        }
        return connection;
    }
}


    
