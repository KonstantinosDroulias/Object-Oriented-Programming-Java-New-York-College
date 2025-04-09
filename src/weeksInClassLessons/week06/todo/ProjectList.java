package weeksInClassLessons.week06.todo;

import java.sql.Connection;
import java.util.ArrayList;

public class ProjectList {
    private ArrayList<Project> projects;

    public ProjectList() {
        projects = new ArrayList<>();
    }

    public void loadProjects() {
        DBConnection db = new DBConnection();
        Connection conn = db.getConnection();
        if (conn != null) {
            String query = "SELECT * FROM Project";

        }
    }
}
