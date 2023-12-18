package Database;

import java.sql.Connection;
import java.sql.DriverManager;
<<<<<<< HEAD
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
=======

>>>>>>> branch 'master' of https://github.com/osanchezGSU/CIS3270_PROJECT.git

public class UserDBTEST {
	
	public Connection databaseLink; 
	
	public Connection getConnection() {
		
		String databaseName = "AeroBookings";
		String databaseUser = "root";
		String databasePassword = "root";
		String url = "jdbc:mysql://localhost/" + databaseName;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return databaseLink;
	}
	
	public void getUsername() {
		
		UserDBTEST connectNow = new UserDBTEST();
        Connection connectDB = connectNow.getConnection();
        String query = "SELECT Username FROM Users WHERE Username = ";
		
		
	}
}

	
	