package weeksInClassLessons.week03.courierapp;

import java.util.Scanner;

public class OrderMenu {
    public static void main(String[] args) {
        OrderList orderList = new OrderList();
        Scanner input = new Scanner(System.in);

        int choice = 0;
        do {
            System.out.println("1. Add Order");
            System.out.println("2. Complete Order");
            System.out.println("3. Incomplete Orders");
            System.out.println("0. Exit");
            System.out.print("Choose -> ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("ID: ");
                    int id = input.nextInt();
                    input.nextLine(); // clear the buffer
                    System.out.print("Name: ");
                    String name = input.nextLine();
                    System.out.print("Address: ");
                    String address = input.nextLine();
                    orderList.addOrder(id, name, address);
                    break;
                case 2:
                    System.out.print("ID: ");
                    id = input.nextInt();
                    orderList.completeOrder(id);
                    break;
                case 3:
                    orderList.printIncomplete();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }

        } while(choice != 0);
    }
}
