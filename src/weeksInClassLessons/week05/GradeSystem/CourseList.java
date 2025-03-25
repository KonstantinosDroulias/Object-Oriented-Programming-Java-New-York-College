package weeksInClassLessons.week05.GradeSystem;

import java.util.ArrayList;

public class CourseList {
    private ArrayList<Course> courses;

    public void displayCourses() {
        for(Course course: courses) {
            System.out.println();
        }
    }
}
