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

    public static Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connected to the AeroBookings database!");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to connect to the database.");
        }
    }

    public static void retrieveUsersData() {
        try (Connection connection = connect()) {
            String query = "SELECT id, first_name, last_name, address, zip_code, state, username, password, email, ssn FROM Users";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int Id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                String zipCode = resultSet.getString("zip_code");
                String state = resultSet.getString("state");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String ssn = resultSet.getString("ssn");

                System.out.println("User ID: " + Id +
                        ", Name: " + firstName + " " + lastName +
                        ", Address: " + address +
                        ", Zip Code: " + zipCode +
                        ", State: " + state +
                        ", Username: " + username +
                        ", Password: " + password +
                        ", Email: " + email +
                        ", SSN: " + ssn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        retrieveUsersData();
    }
}

    
