package finalAssigments.StoreStorageApp.SQLQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArrayFillUtil {

    public static ArrayList<String> fillArray(ArrayList<String> arr, Connection conn, String columnValue, String tableName) {
        try {
            String sql = "SELECT " + columnValue + " FROM " + tableName;
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                arr.add(rs.getString(1));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arr;
    }
}
