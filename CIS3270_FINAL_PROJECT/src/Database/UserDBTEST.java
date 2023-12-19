package Database;

import java.sql.Connection;
import java.sql.DriverManager;
<<<<<<< HEAD
import java.sql.PreparedStatement;
import java.sql.ResultSet;
=======
>>>>>>> branch 'master' of https://github.com/osanchezGSU/CIS3270_PROJECT.git
import java.sql.SQLException;
<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/osanchezGSU/CIS3270_PROJECT.git

public class UserDBTEST {
	
	public Connection databaseLink; 
	
	public Connection getConnection() {
		
		String databaseName = "AeroBookings";
		//String databaseUser = "root";//
		//String databasePassword = "root";//
		//String url = "jdbc:mysql://localhost/" + databaseName;//
		String url = "jdbc:mysql://database-1.cvnfcprgmyxr.us-east-2.rds.amazonaws.com:3306/" + databaseName;
		String databaseUser = "admin";
		String databasePassword = "adminadmin";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return databaseLink;
	}
}
	
	