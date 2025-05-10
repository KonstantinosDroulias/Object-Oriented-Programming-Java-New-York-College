package finalAssigments.StoreStorageApp.SQLQueries;

import finalAssigments.StoreStorageApp.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindIDUtil {

    public static int findID(String columnID, String tableName, String columnName, String searchWord) {
        try {
            DBConnection db = new DBConnection();
            Connection conn = db.getConnection();

            String query = "SELECT " + columnID + " FROM " + tableName + " WHERE " + columnName + " = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, searchWord);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                System.out.println("No result found for: " + searchWord);
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("Error during findID(String): " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    public static int findID(String columnID, String tableName, String columnName, int searchValue) {
        try {
            DBConnection db = new DBConnection();
            Connection conn = db.getConnection();

            String query = "SELECT " + columnID + " FROM " + tableName + " WHERE " + columnName + " = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, searchValue);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                System.out.println("No result found for value: " + searchValue);
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("Error during findID(int): " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
}
