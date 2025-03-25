package weeksTasks.week01.CarRental;

import java.util.ArrayList;
import java.util.Scanner;

public class CarRental {
    static int carIndex;

    static Car Raptor = new Car("Ford Raptor", "ABC123", 20000);
    static Car RAV4 = new Car("Toyota RAV4", "ABC456", 24000);
    static Car LandCruiser = new Car("Toyota Land Cruiser", "ABC789", 15000);
    static Car TT = new Car("Audi TT", "DEF123", 32000);
    static Car SLK = new Car("Mercedes SLK", "DEF456", 21000);

    static ArrayList<Car> cars = new ArrayList<>();

    public static void main(String[] args) {

        cars.add(Raptor);
        cars.add(RAV4);
        cars.add(LandCruiser);
        cars.add(TT);
        cars.add(SLK);

        boolean exit = false;
        do {
            System.out.println("*************");
            System.out.println("Car Rental Menu");
            System.out.println("*************");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a car");
            System.out.println("3. Display Cars");
            System.out.println("4. Exit");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    rentCar();
                    break;
                case 2:
                    returnCar();
                    break;
                case 3:
                    displayCars();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } while (!exit);
        System.out.println("Bye");
    }

    static int searchCar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Car Plate: ");
        String numberPlate = scanner.nextLine();

        for (int i = 0; i < cars.size(); i++) {
            String plate = cars.get(i).getPlate();
            if (plate.equals(numberPlate)) {
                System.out.println("Model: " + cars.get(i).getModel() + " Plate Number: " + cars.get(i).getPlate() + " Kilometers: " + cars.get(i).getKm() + " Rental: " + (cars.get(i).isRented() ? "Not Available" : "Available"));
                return i;
            }
        }
            System.out.println("Invalid plate");
            return -1;
    }

    static void rentCar() {
        do {
            carIndex = searchCar();
        } while(carIndex == -1);

        if (!cars.get(carIndex).isRented()) {
            System.out.print("Car is Available for rental, want to proceed y/n? ");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("y")) {
                System.out.print("Give amount of days you want to rent: ");
                int days = scanner.nextInt();
                cars.get(carIndex).setRented(true);
                System.out.println(cars.get(carIndex).getModel() + " has been rented for " + days + " days");
            } else if (answer.equalsIgnoreCase("n")) {
                System.out.println("Returning to main menu");
            }
        }
    }

    static void returnCar() {
        do {
            carIndex = searchCar();
        } while(carIndex == -1);

        if (cars.get(carIndex).isRented()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print(cars.get(carIndex).getModel() + " with " + cars.get(carIndex).getPlate() + " plate number is Available for returning, want to proceed y/n? ");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("y")) {
                int km;
                do {
                    System.out.print("Update returned km: ");
                    km = scanner.nextInt();
                    cars.get(carIndex).setKM(km);
                    cars.get(carIndex).setRented(false);
                } while (km >= cars.get(carIndex).getKm());
                System.out.println("Car km updated and returned successfully");
            } else if (answer.equalsIgnoreCase("n")) {
                System.out.println("Returning to main menu");
            }
        } else {
            System.out.println("Car isn't rented, call 911 immediately");
        }
    }

    static void displayCars() {
        for (Car car : cars) {
            System.out.println("Model: " + car.getModel() + " Plate Number: " + car.getPlate() + " Kilometers: " + car.getKm() + " Rental: " + (car.isRented() ? "Not Available" : "Available"));
        }
    }
}
