package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class UsersDB {
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/AeroBookings"; // Updated database name to AeroBookings
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "celes1213";
 
    // Method to establish a connection to the database
    public static Connection connect() throws SQLException {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the connection
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connected to the AeroBookings database!"); // Print message upon successful connection
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to connect to the database.");
        }
    }
 
    // Method to retrieve user data from the Users table
    public static void retrieveUsersData() {
        try (Connection connection = connect()) {
            // Query to retrieve specific data from the Users table
            String query = "SELECT Id, first_name, last_name, address, zip_code, state, username, password, email, ssn, security_questions, security_answer, user_type FROM Users";
            
            // Create a prepared statement with the query
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
 
            // Process the retrieved data
            while (resultSet.next()) {
                // Retrieve data for each user
                int userId = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                String zipCode = resultSet.getString("zip_code");
                String state = resultSet.getString("state");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String ssn = resultSet.getString("ssn");
                String securityQuestions = resultSet.getString("security_questions");
                String securityAnswer = resultSet.getString("security_answer");
                String userType = resultSet.getString("user_type");
 
                // Process retrieved data (you can modify this according to your needs)
                System.out.println("User ID: " + userId +
                        ", Name: " + firstName + " " + lastName +
                        ", Address: " + address +
                        ", Zip Code: " + zipCode +
                        ", State: " + state +
                        ", Username: " + username +
                        ", Password: " + password +
                        ", Email: " + email +
                        ", SSN: " + ssn +
                        ", Security Question: " + securityQuestions +
                        ", Security Answer: " + securityAnswer +
                        ", User Type: " + userType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }
 
    public static void main(String[] args) {
        // Call the method to retrieve users' data or perform other tasks
        retrieveUsersData();
    }
}