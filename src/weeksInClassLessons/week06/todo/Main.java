package weeksInClassLessons.week06.todo;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        do {
            System.out.println("*************");
            System.out.println("Todo App");
            System.out.println("*************");
            System.out.println("1. Add Project");
            System.out.println("2. Add Task");
            System.out.println("3. View All");
            System.out.println("4. Delete Task");
            System.out.println("0. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } while (!exit);
        System.out.println("Bye");
    }
    }

