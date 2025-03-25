package weeksInClassLessons.week05.FileReadingWritting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriteClass {
    public static void main(String[] args) throws IOException {
        File file = new File("data.txt");
        FileWriter writer = new FileWriter(file);

        System.out.println("WRITTING INTO THE FILE");
        writer.write("Hello World\n");
        writer.write("Writting into a file\n");
        writer.close();

        System.out.println("DONE");
    }
}
