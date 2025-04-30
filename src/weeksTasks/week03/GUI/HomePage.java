package weeksTasks.week03.GUI;

import weeksTasks.week03.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame implements ActionListener {

    JButton adminLogin;
    Login loginPopUp = null;

    public HomePage() {
        // Load books from DB
        BooksList booksList = new BooksList();
        booksList.loadBooksFromDB();

        // Set up main window (this JFrame)
        this.setTitle("Book Store");
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setIconImage(new ImageIcon("book.png").getImage());
        this.getContentPane().setBackground(new Color(0xDDD8CF));
        this.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();
        JPanel westPanel = new JPanel();

        this.add(centerPanel,BorderLayout.CENTER);
        this.add(northPanel,BorderLayout.NORTH);
        this.add(southPanel,BorderLayout.SOUTH);
        this.add(westPanel,BorderLayout.WEST);

        northPanel.setBackground(new Color(0x006A4E));
        southPanel.setBackground(new Color(0x006A4E));
        northPanel.setOpaque(true);
        southPanel.setOpaque(true);

        northPanel.setPreferredSize(new Dimension(1000, 30));
        southPanel.setPreferredSize(new Dimension(1000, 30));
        westPanel.setPreferredSize(new Dimension(200, 100));

        northPanel.setLayout(new BorderLayout());
        southPanel.setLayout(new BorderLayout());
        //westPanel.setLayout(new BorderLayout());

        /* Header Content */

        JLabel logoLabel = new JLabel("Welcome To Our Book Store", SwingConstants.CENTER);
        logoLabel.setForeground(Color.WHITE);
        northPanel.add(logoLabel, BorderLayout.CENTER);

        adminLogin = new JButton("Login");
        adminLogin.addActionListener(this);

        northPanel.add(adminLogin, BorderLayout.EAST);

        /* Footer Content */
        JLabel copyRight = new JLabel("© 2025 Book Store. All rights reserved.");
        copyRight.setHorizontalAlignment(SwingConstants.CENTER);
        copyRight.setVerticalAlignment(SwingConstants.CENTER);
        copyRight.setForeground(Color.WHITE);

        southPanel.add(copyRight);

        /* Genres Content */
        JLabel heading = new JLabel("Select Genre:");
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setVerticalAlignment(SwingConstants.CENTER);
        JLabel booksCount = new JLabel("Available Books: " + String.valueOf(booksList.getBookListSize()));
        booksCount.setHorizontalAlignment(SwingConstants.CENTER);
        booksCount.setVerticalAlignment(SwingConstants.CENTER);

        westPanel.add(booksCount);
        westPanel.add(heading);

        // Main panel to hold all book cards
        JPanel gridLayout = new JPanel();
        gridLayout.setLayout(new GridLayout(0, 3, 15, 15)); // auto row, 3 columns
        gridLayout.setBackground(new Color(0xDDD8CF));
        gridLayout.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.add(gridLayout,BorderLayout.CENTER);

        // Loop through books and create panels
        for (Book book : booksList.getBookList()) {
            JPanel bookCard = new JPanel();
            bookCard.setLayout(new BoxLayout(bookCard, BoxLayout.Y_AXIS));
            //bookCard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            bookCard.setBackground(new Color(0xDDD8CF));

            // Book image
            ImageIcon rawIcon = new ImageIcon("src/weeksTasks/week03/images/bookcovers/" + book.getBookImage());
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

            JLabel typeOfBook = new JLabel("Unknown");
            typeOfBook.setAlignmentX(Component.CENTER_ALIGNMENT);

            bookCard.add(typeOfBook);
            bookCard.add(imgLabel);
            bookCard.add(titleLabel);
            bookCard.add(authorLabel);
            bookCard.add(priceLabel);

            // Add child-specific data
            if (book instanceof PrintedBook pb) {
                JLabel pagesLabel = new JLabel("Pages: " + pb.getPages());
                JLabel stockLabel = new JLabel("Stock: " + pb.getStock());
                typeOfBook.setText("[Hard Cover]");
                pagesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                stockLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                bookCard.add(pagesLabel);
                bookCard.add(stockLabel);
            } else if (book instanceof Ebook eb) {
                JLabel kbLabel = new JLabel("Size: " + eb.getKb() + " KB");
                typeOfBook.setText("[Ebook]");
                kbLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                bookCard.add(kbLabel);
            } else if (book instanceof AudioBook ab) {
                JLabel durLabel = new JLabel("Duration: " + ab.getDuration() + "hrs");
                typeOfBook.setText("[Audio]");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == adminLogin) {
            if (loginPopUp == null || !loginPopUp.isDisplayable()) {
                loginPopUp = new Login();
                loginPopUp.setLocationRelativeTo(HomePage.this);
                loginPopUp.setVisible(true);
            } else {
                loginPopUp.toFront(); // Optionally bring the existing window to front
            }
        }
    }
}
