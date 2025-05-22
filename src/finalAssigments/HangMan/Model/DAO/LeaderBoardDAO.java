package finalAssigments.HangMan.Model.DAO;

import finalAssigments.HangMan.Model.DBConnection;
import finalAssigments.HangMan.Model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeaderBoardDAO {
    private DBConnection db = new DBConnection();
    private Connection conn = db.getConnection();

    public LeaderBoardDAO() {
        // constructor can remain empty or used for future init
    }

    public List<UserModel> getAllUsers() {
        List<UserModel> users = new ArrayList<>();

        try {
            String usersQuery = "SELECT Username, Score FROM Users";
            PreparedStatement usersStmt = conn.prepareStatement(usersQuery);
            ResultSet rs = usersStmt.executeQuery();

            while (rs.next()) {
                String foundUsername = rs.getString("Username");
                int userScore = rs.getInt("Score");
                users.add(new UserModel(foundUsername, userScore));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
