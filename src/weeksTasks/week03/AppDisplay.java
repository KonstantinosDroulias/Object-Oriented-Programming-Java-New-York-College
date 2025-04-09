package weeksTasks.week03;

import javax.swing.*;
import java.awt.*;

public class AppDisplay extends JFrame {

    public AppDisplay() {
        // Load books from DB
        BooksList booksList = new BooksList();
        booksList.loadBooksFromDB();

        // Set up main window (this JFrame)
        this.setTitle("Book Store");
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setIconImage(new ImageIcon("book.png").getImage());
        this.getContentPane().setBackground(new Color(0xDDD8CF));
        this.setLayout(new BorderLayout());

        // Main panel to hold all book cards
        JPanel gridLayout = new JPanel();
        gridLayout.setLayout(new GridLayout(0, 3, 15, 15)); // auto row, 3 columns
        gridLayout.setBackground(new Color(0xDDD8CF));
        gridLayout.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Loop through books and create panels
        for (Book book : booksList.getBookList()) {
            JPanel bookCard = new JPanel();
            bookCard.setLayout(new BoxLayout(bookCard, BoxLayout.Y_AXIS));
            bookCard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            bookCard.setBackground(Color.WHITE);

            // Book image
            ImageIcon rawIcon = new ImageIcon("48-laws.jpg");
            Image scaledImage = rawIcon.getImage().getScaledInstance(120, 160, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(scaledImage));
            imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Info
            JLabel titleLabel = new JLabel(book.getTitle());
            JLabel authorLabel = new JLabel("By: " + book.getAuthor());
            JLabel priceLabel = new JLabel("Price: $" + book.getPrice());

            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            bookCard.add(imgLabel);
            bookCard.add(titleLabel);
            bookCard.add(authorLabel);
            bookCard.add(priceLabel);

            // Add child-specific data
            if (book instanceof PrintedBook pb) {
                JLabel pagesLabel = new JLabel("Pages: " + pb.getPages());
                JLabel stockLabel = new JLabel("Stock: " + pb.getStock());
                pagesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                stockLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                bookCard.add(pagesLabel);
                bookCard.add(stockLabel);
            } else if (book instanceof Ebook eb) {
                JLabel kbLabel = new JLabel("Size: " + eb.getKb() + " KB");
                kbLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                bookCard.add(kbLabel);
            } else if (book instanceof AudioBook ab) {
                JLabel durLabel = new JLabel("Duration: " + ab.getDuration() + "hrs");
                durLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                bookCard.add(durLabel);
            }

            gridLayout.add(bookCard);
        }

        // Add grid to scroll pane
        JScrollPane scrollPane = new JScrollPane(gridLayout);
        scrollPane.setBorder(null);
        this.add(scrollPane, BorderLayout.CENTER);

        // Show window
        this.setVisible(true);
    }
}
