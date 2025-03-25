package weeksInClassLessons.week02;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class StudentAverage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int courses = 0;
        boolean notWrong = false;

        do {
            try {
                System.out.print("Enter number of courses: ");
                courses = sc.nextInt();
                notWrong = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number");
                sc.nextLine();
            }
        } while (!notWrong);

        Random rd = new Random();
        int sum = 0;
        for (int i = 0; i < courses; i++) {
            int randomGrades = rd.nextInt(100);
            System.out.print(" Your Grade: " + randomGrades);
            sum += randomGrades;
        }
        System.out.println(sum / courses);

    }
}
