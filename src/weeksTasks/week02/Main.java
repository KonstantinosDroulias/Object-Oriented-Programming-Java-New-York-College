package weeksTasks.week02;

import weeksTasks.week01.CarRental.Car;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static String teamName;
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Team> teams = new ArrayList<>();

    public static void main(String[] args) {

        boolean exit = false;
        do {
            System.out.println("*************");
            System.out.println("Football League of Elbonia");
            System.out.println("*************");
            System.out.println("1. Add Game");
            System.out.println("2. Team Performance");
            System.out.println("0. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addGame();
                    break;
                case 2:
                    teamsPerformance();
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

    static boolean choice() {
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("y")) {
            return true;
        } else {
            return false;
        }
    }

    static int searchTeam() {
        do {
            System.out.print("Enter team's name: ");
            teamName = scanner.nextLine();
            System.out.print("You have entered " + teamName + " y/n? ");
        } while(!choice());
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getName().equalsIgnoreCase(teamName)) {
                return i;
            }
        }
        return -1;
    }

    static String gameOutcome(int index1, int index2) {
        System.out.print("Did " + teams.get(index1).getName() + " Win, Lose, or did it ended in Draw: " );
        String outcome;
        do {
            outcome = scanner.nextLine();

            switch (outcome) {
                case "Draw":
                    teams.get(index1).setDraws();
                    teams.get(index2).setDraws();
                    break;
                case "Lose":
                    teams.get(index1).setLosses();
                    teams.get(index2).setWins();
                    break;
                case "Win":
                    teams.get(index1).setWins();
                    teams.get(index2).setLosses();
                    break;
                default:
                    System.out.println("Invalid option, please type 'Win', or 'Lose', or 'Draw' ");
            }
        } while(!outcome.equals("Win") && !outcome.equals("Lose") && !outcome.equals("Draw"));

        System.out.println("Score was updated, press 2 in the menu to see the results");
        return outcome;
    };

    static int addTeam() {
        if (searchTeam() == -1) {
            System.out.print("Team does not exist, do you want to add new team y/n? ");
            if(choice()) {
                Team team = new Team(teamName, 0, 0, 0);
                teams.add(team);
                return teams.size() - 1;
            } else {
                return searchTeam();
            }
        } else {
            return searchTeam();
        }
    }

    static void addGame() {
        System.out.println("Adding new game...");
        System.out.println("Home Team: ");
        int teamPosition01 = addTeam();
        if (teamPosition01 == -1) {return;}
        System.out.println("Away Team:");
        int teamPosition02 = addTeam();
        if (teamPosition02 == -1) {return;}
        gameOutcome(teamPosition01, teamPosition02);
    }

    static void teamsPerformance() {
        for (int i = 0; i < teams.size(); i++) {
            System.out.println("Team " + teams.get(i).getName() + " performance " + teams.get(i).getWins() + " wins and " + teams.get(i).getLosses() + " losses"  + teams.get(i).getDraws() + " draws");
        }
    }

}