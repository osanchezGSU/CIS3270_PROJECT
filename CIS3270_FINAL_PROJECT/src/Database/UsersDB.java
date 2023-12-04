package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersDB {
public Connection databaseLink;
	
	public Connection getConnected() {
		String databaseName = "Demo";
		String databaseUser = "root";
		String databasePassword = "Sanchez01";
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
*/
}
