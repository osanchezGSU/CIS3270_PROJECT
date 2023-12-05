package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDB {

<<<<<<< HEAD
	
	
	public static void main(String[] args) {
    	
    	try {
    		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/AeroBookings","root","root");
    		Statement st = con.createStatement();
    		String query = "select * from Admin";
    		ResultSet rs = st.executeQuery(query);
    		while(rs.next()) {
    			System.out.println(rs.getString(1)+ " "+ rs.getString(2));
    		}
    		con.close();
    	}catch(SQLException e) {
    		System.out.println("Error");
    	} catch(Exception e) {
    			
=======
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/AeroBookings"; 
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
>>>>>>> branch 'master' of https://github.com/osanchezGSU/CIS3270_PROJECT.git

    public static void main(String[] args) {
        // Call a method to retrieve admin data or perform other tasks
        retrieveAdminData();
    }

    public static void retrieveAdminData() {
        try (Connection connection = getConnection()) {
            // Query to retrieve specific data from the Bookings table 
            String query = "SELECT admin_id, username, password FROM Admin";

            // Create a prepared statement with the query
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the retrieved data
            while (resultSet.next()) {
                // Retrieve data for each admin
                int adminId = resultSet.getInt("admin_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                // Process retrieved data  
                System.out.println("Admin ID: " + adminId +
                        ", Username: " + username +
                        ", Password: " + password);
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


