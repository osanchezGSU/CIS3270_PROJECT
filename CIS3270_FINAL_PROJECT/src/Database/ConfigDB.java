package Database;

public class ConfigDB {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/AeroBookings";
    private static final String DB_USER = "mkunigonis";
    private static final String DB_PASSWORD = "root2";
 
    public static String getDBURL() {
        return DB_URL;
    }
 
    public static String getDBUser() {
        return DB_USER;
    }
 
    public static String getDBPassword() {
        return DB_PASSWORD;
    }
}
