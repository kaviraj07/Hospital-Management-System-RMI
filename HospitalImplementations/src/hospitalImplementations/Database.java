package hospitalImplementations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static Connection getConnection() {
        Connection conn = null;
        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost/hospital_system", "root",
                    "");
            System.out.println("Connected to database...");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return conn;
    }

}