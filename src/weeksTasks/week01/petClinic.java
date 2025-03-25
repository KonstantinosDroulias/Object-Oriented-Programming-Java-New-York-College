package weeksTasks.week01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class petClinic {

    static Scanner scanner = new Scanner(System.in);

    static HashMap<String, ArrayList<String>> petTypes = new HashMap<>();

    public static void main(String[] args) {

        int menuOption;
        boolean quit = false;

        do {
            System.out.println("******************");
            System.out.println("Pet Clinic Animals");
            System.out.println("******************");

            System.out.println("1. Search Type");
            System.out.println("2. Add new Type");
            System.out.println("3. Add new Pet");
            System.out.println("4. Exit");

            System.out.print("Select option: ");
            menuOption = scanner.nextInt();

            switch (menuOption) {
                case 1:
                    searchType(true);
                    break;
                case 2:
                    addType(null);
                    break;
                case 3:
                    addPet();
                    break;
                case 4:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid option, please try again");
            }
        } while (!quit);
        System.out.println("Bye!");
    }

    static String searchType(boolean searchEmpty) {
        scanner.nextLine();

        System.out.print("Search Pet type: ");
        String petType = scanner.nextLine();
        if (petTypes.containsKey(petType)) {
            System.out.println("Pet type: " + petType);
            ArrayList<String> pets = petTypes.get(petType);

            if (pets.isEmpty() && searchEmpty) {
                System.out.println("No pets found under this type.");
            } else {
                for (String petName : pets) {
                    System.out.println("  - " + petName);
                }
            }
            return petType;
        } else {
            do {
                System.out.println("Pet Type does not exist, do you want to add new pet type y/n?");
                String answer = scanner.nextLine();
                if (answer.equals("y") || answer.equals("yes")) {
                    addType(petType);
                    return petType;
                } else if (answer.equals("n") || answer.equals("no")) {
                    return null;
                } else {
                    System.out.print("Invalid Input, please type 'y' or 'n'?");
                }
            } while (true);

        }
    }

    static void addType(String type) {
        //scanner.nextLine();

        if (type == null) {
            System.out.print("Enter pet type: ");
            String petType = scanner.nextLine();
            petTypes.put(petType, new ArrayList<>());
        } else {
            do {
                System.out.print("Want to add " + type + " as pet type y/n?");
                String answer = scanner.nextLine();
                if (answer.equals("y") || answer.equals("yes")) {
                    System.out.println(type + " is added as a pet type");
                    petTypes.put(type, new ArrayList<>());
                    break;
                } else if (answer.equals("n") || answer.equals("no")) {
                    System.out.print("Enter new pet type: ");
                    type = scanner.nextLine();
                    petTypes.put(type, new ArrayList<>());
                    break;
                } else {
                    System.out.print("Invalid Input, please type 'y' or 'n'?");
                    break;
                }
            } while (true);


        }

    }

    static void addPet() {
        String petType = searchType(false);
        if (petType == null) {
            System.out.println("Pet not added because no valid type was selected.");
            return;
        }

        System.out.print("Please enter the name of the pet: ");
        String name = scanner.nextLine();

        petTypes.get(petType).add(name);
        System.out.println(name + " has been added to the " + petType + " category.");
    }

}
