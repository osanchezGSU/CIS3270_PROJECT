package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDB {
   

	
	
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
    			

    	}
	}
	}
    
    

