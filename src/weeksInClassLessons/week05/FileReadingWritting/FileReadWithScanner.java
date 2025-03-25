package weeksInClassLessons.week05.FileReadingWritting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReadWithScanner {
    public static void main(String[] args) {
        try {
            File file = new File("data.txt");
            Scanner scanner = new Scanner(file);

            String line;

            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException fe) {
            System.out.println(fe.getMessage());
        }
    }
}
