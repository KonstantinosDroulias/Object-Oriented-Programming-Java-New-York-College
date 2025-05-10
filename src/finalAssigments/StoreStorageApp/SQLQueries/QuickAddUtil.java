package finalAssigments.StoreStorageApp.SQLQueries;

import finalAssigments.StoreStorageApp.DBConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuickAddUtil {

    public void addValue(Connection conn, String tableName, String columnName, String data) {
        try {
            String query = "INSERT INTO " + tableName + " (" + columnName + ") VALUES (?);";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, data);
            int rowsInserted = stmt.executeUpdate();
            stmt.close();
            System.out.println(rowsInserted);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data could not be inserted");
            e.printStackTrace();
        }

    }
}
