package weeksTasks.week03;

import weeksTasks.week02.Team;

import java.util.ArrayList;
import java.util.Scanner;

public class BookStore {

    public static void main(String[] args) {
        AppDisplay home = new AppDisplay();
        BooksList booksList = new BooksList();
        booksList.loadBooksFromDB();
        booksList.displayAllBooks();
    }

}
