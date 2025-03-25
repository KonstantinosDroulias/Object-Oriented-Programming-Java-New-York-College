package weeksTasks.week01;

import java.util.Scanner;

public class aRectangle {
    public static void main(String[] args) {

        int width = 0;
        int height = 0;

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Enter the width: ");
            width = scanner.nextInt();
            System.out.print("Enter the height: ");
            height = scanner.nextInt();
            if (width < 0 || height < 0) {
                System.out.println();
            }
        } while (width < 0 || height < 0);

        scanner.close();

        for (int i = 0 ; i < height ; i++) {
            for (int j = 0 ; j < width ; j++) {
                if (i == 0 || i == height - 1) {
                    if (j == width - 1) {
                        System.out.println("*");
                    } else {
                        System.out.print("*");
                    }
                } else if (j == 0) {
                    System.out.print("*");
                } else if (j == width - 1){
                    System.out.println("*");
                } else {
                    System.out.print(" ");
                }


            }


        }


    }
}
