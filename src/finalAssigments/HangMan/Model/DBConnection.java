package finalAssigments.HangMan.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/JavaHangMan";
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
