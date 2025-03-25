package weeksInClassLessons.week02;

import java.util.Scanner;

public class AgeChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Your age: ");
        int age = scanner.nextInt();

        if (age <= 18) {
            System.out.print("Young");
        }
        else if (age <= 67) {
            System.out.print("Grown Up");
        }
    }
}
