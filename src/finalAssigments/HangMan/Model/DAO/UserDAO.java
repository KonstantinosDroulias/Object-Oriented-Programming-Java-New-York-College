package finalAssigments.HangMan.Model.DAO;


import finalAssigments.HangMan.Model.DBConnection;
import finalAssigments.HangMan.Model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private DBConnection db = new DBConnection();
    private Connection conn = db.getConnection();

    public UserModel login(String Username, String Password) {
        try {
            String loginQuery = "SELECT Username, Userpass, Score FROM Users WHERE Username = ? AND Userpass = ?";
            PreparedStatement loginPS = conn.prepareStatement(loginQuery);
            loginPS.setString(1, Username);
            loginPS.setString(2, Password);
            ResultSet rs = loginPS.executeQuery();
            if (rs.next()) {
                String foundUsername = rs.getString("Username");
                int userScore = rs.getInt("Score");
                return new UserModel(foundUsername, userScore);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserModel register(String Username, String Password) {
        try {
            String registerQuery = "INSERT INTO Users (Username, Userpass) VALUES (?, ?)";
            PreparedStatement registerPS = conn.prepareStatement(registerQuery);
            registerPS.setString(1, Username);
            registerPS.setString(2, Password);
            int rowsInserted = registerPS.executeUpdate();

            if (rowsInserted > 0) {
                return new UserModel(Username, 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
