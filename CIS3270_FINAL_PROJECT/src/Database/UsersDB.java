package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersDB {
	public static void main(String[] args) {
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
    			

    	}
    }
}
