package weeksInClassLessons.week06.todo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Project {
    private int id;
    private String title;
    private ArrayList<Task> tasks;

    public Project(int id, String title) {
        this.id = id;
        this.title = title;
        this.tasks = new ArrayList<>();
        //loadTasks();
    }

    public void loadTasks(ArrayList<Task> tasks) {
        DBConnection db = new DBConnection();
        Connection conn = db.getConnection();
        if (conn != null) {
            String query = "SELECT * FROM tasks WHERE projectID=" + id;
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Task task = new Task(rs.getInt(0),
                                rs.getString(1),
                                rs.getBoolean(2));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
