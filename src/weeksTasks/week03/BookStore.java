package weeksTasks.week03;

import weeksTasks.week03.GUI.HomePage;

public class BookStore {

    public static void main(String[] args) {
        HomePage home = new HomePage();
        BooksList booksList = new BooksList();
        booksList.loadBooksFromDB();
        booksList.displayAllBooks();
    }

}
