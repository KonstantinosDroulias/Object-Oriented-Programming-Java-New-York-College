package weeksTasks.week03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BooksList {

    private List<Book> bookList;

    public BooksList() {
        this.bookList = new ArrayList<>();
    }

    public void loadBooksFromDB() {
        DBConnection db = new DBConnection();
        Connection conn = db.getConnection();

        if (conn == null) {
            System.out.println("Failed to connect to database.");
            return;
        }

        try {
            // Printed Books
            String printedQuery = "SELECT b.BookID, b.Title, b.BookImage, b.Author, b.Price, b.ISBN, p.Pages, p.stock " +
                    "FROM Book b JOIN PrintedBook p ON b.BookID = p.BookID";
            PreparedStatement ps = conn.prepareStatement(printedQuery);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PrintedBook pb = new PrintedBook(
                        rs.getInt("BookID"),
                        rs.getString("Title"),
                        rs.getString("BookImage"),
                        rs.getString("Author"),
                        rs.getInt("Price"),
                        rs.getLong("ISBN"),
                        rs.getInt("Pages"),
                        rs.getInt("stock")
                );
                bookList.add(pb);
            }

            // Ebooks
            String ebookQuery = "SELECT b.BookID, b.Title, b.BookImage, b.Author, b.Price, b.ISBN, e.kb " +
                    "FROM Book b JOIN Ebook e ON b.BookID = e.BookID";
            ps = conn.prepareStatement(ebookQuery);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ebook eb = new Ebook(
                        rs.getInt("BookID"),
                        rs.getString("Title"),
                        rs.getString("BookImage"),
                        rs.getString("Author"),
                        rs.getInt("Price"),
                        rs.getLong("ISBN"),
                        rs.getInt("kb")
                );
                bookList.add(eb);
            }

            // Audiobooks
            String audioQuery = "SELECT b.BookID, b.Title, b.BookImage, b.Author, b.Price, b.ISBN, a.Duration " +
                    "FROM Book b JOIN AudioBook a ON b.BookID = a.BookID";
            ps = conn.prepareStatement(audioQuery);
            rs = ps.executeQuery();
            while (rs.next()) {
                AudioBook ab = new AudioBook(
                        rs.getInt("BookID"),
                        rs.getString("Title"),
                        rs.getString("BookImage"),
                        rs.getString("Author"),
                        rs.getInt("Price"),
                        rs.getLong("ISBN"),
                        rs.getString("Duration")
                );
                bookList.add(ab);
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println("❌ Error loading books");
            e.printStackTrace();
        }
    }

    public void displayAllBooks() {
        for (Book b : bookList) {
            b.displayInfo();
        }
    }

    public List<Book> getBookList() {
        return bookList;
    }


}
