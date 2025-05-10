package finalAssigments.StoreStorageApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/StoreStorageJavaApp";
        String user = "test";
        String password = "test";
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to MySQL successfully!");
            return conn;
        } catch (SQLException e) {
            System.out.println("Connection with Server Error");
            e.printStackTrace();
            return null;
        }
    }
}
