package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Main {

	public static void main(String[] args) throws Exception{
		createCustomerTable();
		
		

	}
	
	public static void createCustomerTable () throws Exception{
		try {
			Connection con = getConnection();
			PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS tablename(id integer NOT NULL AUTO_INCREMENT, first varchar(255), last varchar(255), age int, PRIMARY KEY (id)) ");
			create.executeUpdate();
			
			
		}catch(Exception e) {System.out.println(e);}
		finally {
			System.out.println("Function complete."); 
			};
	}
	
	

	public static Connection getConnection() throws Exception{
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/AeroBookings";
			String username = "root";
			String password = "root"
;
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connected");
			return conn;
		} catch (Exception e) {System.out.println(e);}
		
		return null;
					
		
		
	}
	

}
