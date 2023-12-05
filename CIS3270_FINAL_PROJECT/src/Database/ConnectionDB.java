package Database;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;
 
public class ConnectionDB {

    public static void main(String[] args) {

        String host = "localhost"; // Replace with the database host if it's different

        String port = "3306"; // Replace with the database port if it's different

        String dbName = "AeroBookings"; // Replace with the database name

        String username = "mkunigonis"; // Replace with the database username

        String password = "root2"; // Replace with the database password
 
        // JDBC URL to connect to the database

        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
 
        try {

            // Establish the connection

            Connection connection = DriverManager.getConnection(url, username, password);

            // Print a message upon successful connection

            System.out.println("Connected to the AeroBookings database!");
 
            // Perform database operations here

            // Close the connection when done

            connection.close();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

}