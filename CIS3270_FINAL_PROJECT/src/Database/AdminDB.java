package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDB {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/AeroBookings";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static void main(String[] args) {
        retrieveAdminData();
    }

    public static void retrieveAdminData() {
        try (Connection connection = getConnection()) {

            String query = "SELECT admin_id, username, password FROM Admin";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int adminId = resultSet.getInt("admin_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                System.out.println("Admin ID: " + adminId +
                        ", Username: " + username +
                        ", Password: " + password);
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
 
    	