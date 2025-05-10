package finalAssigments.StoreStorageApp.SQLQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JoinTableUtil {

    public static void insertJoin(Connection connection, String insertTable, String firstTableIDColumn, String firstTableName, String firstTableMatchColumn, int matchValue, String secondTableIDColumn, String secondTableName, String secondTableMatchColumn, String searchWord) {
        try {
            int firstID = FindIDUtil.findID(firstTableIDColumn, firstTableName, firstTableMatchColumn, matchValue);
            int secondID = FindIDUtil.findID(secondTableIDColumn, secondTableName, secondTableMatchColumn, searchWord);

            if (firstID == -1 || secondID == -1) {
                System.out.println("Could not find IDs.");
                return;
            }

            String sql = "INSERT INTO " + insertTable + " VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, firstID);
            stmt.setInt(2, secondID);
            stmt.executeUpdate();
            stmt.close();

            System.out.println("Join inserted: " + firstID + " | " + secondID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
