package weeksTasks.week03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/bookstore"; // FIXED: '//' -> '://'
        String user = "test";  // your MySQL username
        String password = "test";  // your MySQL password

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to MySQL successfully!");
            return conn;
        } catch (SQLException e) {
            System.out.println("❌ Connection with Server Error");
            e.printStackTrace();  // helps to debug connection problems
            return null;
        }
    }
}
