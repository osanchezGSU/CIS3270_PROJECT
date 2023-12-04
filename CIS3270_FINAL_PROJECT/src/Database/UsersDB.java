package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersDB {

public static void main(String[] args) {
	// Replace these with your actual database information
	String databaseUrl = "jdbc:mysql://localhost:3306/Demo";
	String username = "Osvaldo";
	String password = "GSU";

	try {
	// Load the JDBC driver
	Class.forName("com.mysql.cj.jdbc.Driver");

	// Attempt to establish a connection
	Connection connection = DriverManager.getConnection(databaseUrl, username, password);

	// If the connection is successful, print a success message
	System.out.println("Connected to the database!");


	// Close the connection
	connection.close();
	} catch (ClassNotFoundException e) {
	System.err.println("JDBC driver not found!");
	e.printStackTrace();
	} catch (SQLException e) {
	System.err.println("Error connecting to the database!");
	e.printStackTrace();
	}

public class UsersDB {
public Connection databaseLink;
	
	public Connection getConnected() {
		String databaseName = "Demo";
		String databaseUser = "Osvaldo";
		String databasePassword = "GSU";
		String url = "jdbc:mysql://127.0.0.1:3306/" + databaseName;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return databaseLink;
		}
	

	
/*	public static void main(String[] args) {
    	try {
    		Connection con = DriverManager.getConnection("jdbc:mysql:///AeroBookings","root","celes1213");
    		Statement st = con.createStatement();
    		String query = "select * from Users";
    		ResultSet rs = st.executeQuery(query);
    		while(rs.next()) {
    			System.out.println(rs.getString(1)+ " "+ rs.getString(2));
    		}
    		con.close();
    	}catch(SQLException e) {
    		System.out.println("Error");
    	} catch(Exception e) {
    			

String databaseName = "Demo";
		String databaseUser = "root";
		String databasePassword = "Sanchez01";
		String url = "jdbc:mysql://127.0.0.1:3306/" + databaseName;
		

    	}
    }