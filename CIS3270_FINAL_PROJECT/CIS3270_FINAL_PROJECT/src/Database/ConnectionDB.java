package Database;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;
 
public class ConnectionDB {

    public static void main(String[] args) {

        String host = "localhost"; 

        String port = "3306"; 

        String dbName = "AeroBookings"; 

        String username = "mkunigonis"; 

        String password = "root2"; 

        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
 
        try {

         

            Connection connection = DriverManager.getConnection(url, username, password);

     

            System.out.println("Connected to the AeroBookings database!");
 
         
            connection.close();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

}