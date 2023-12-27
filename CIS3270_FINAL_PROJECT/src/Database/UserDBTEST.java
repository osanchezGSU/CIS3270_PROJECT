package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDBTEST {
	
	public Connection databaseLink; 
	
	public Connection getConnection() {
		
	;
		//String databaseUser = "root";//
		//String databasePassword = "root";//
		//String url = "jdbc:mysql://localhost/" + databaseName;//
		String databaseName = "AeroBookings";	
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
	
	