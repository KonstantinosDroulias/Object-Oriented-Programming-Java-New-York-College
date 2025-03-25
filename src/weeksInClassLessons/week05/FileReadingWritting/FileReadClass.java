package weeksInClassLessons.week05.FileReadingWritting;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileReadClass {
    public static void main(String[] args) {
        try {
            File file = new File("data.txt");
            FileReader reader = new FileReader(file);

            System.out.println("READING FROM FILE");

            System.out.println("DONE");

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }
}
