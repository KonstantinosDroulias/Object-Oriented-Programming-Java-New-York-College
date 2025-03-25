package weeksInClassLessons.week02;

/*
Ask user for two numbers and print sum,
difference, product, and division
*/

import java.util.Scanner;

public class Calculation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("First number: ");
        int a = scanner.nextInt();
        System.out.print("Second number ");
        int b = scanner.nextInt();

        System.out.println("SUM " + (a + b));
        System.out.println("DIV " + (a / (float)b));
    }
}
