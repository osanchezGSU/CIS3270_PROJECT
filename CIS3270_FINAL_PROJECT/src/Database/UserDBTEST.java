package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDBTEST {
    
    private Connection databaseLink;
    
    public Connection getConnection() {
        String databaseName = "AeroBookings";
        String databaseUser = "root";
        String databasePassword = "root";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return databaseLink;
    }

    public void retrieveUserData(String query) {
        try {
            Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            
            // Process the ResultSet or perform necessary operations
            while (result.next()) {
                // Access columns and do something with the data
                String columnData = result.getString("column_name");
                System.out.println(columnData);
            }

            conn.close(); // Close the connection when done
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}

	
	