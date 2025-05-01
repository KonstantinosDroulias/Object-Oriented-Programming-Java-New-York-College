package weeksTasks.week03.GUI.AdminPagePanels;

import weeksTasks.week03.DBConnection;
import weeksTasks.week03.GUI.Components.TextFieldLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AddBooks extends JPanel implements ActionListener {

    JComboBox<String> bookType;
    JPanel objectFieldsPanel;
    CardLayout objectFieldsCardLayout;
    JButton addImageButton;
    JButton addBookButton;
    JLabel fileName;
    DBConnection db = new DBConnection();

    TextFieldLabel bookTitle;
    TextFieldLabel bookAuthor;
    TextFieldLabel ISBN;
    TextFieldLabel price;
    TextFieldLabel pages;
    TextFieldLabel stock;
    TextFieldLabel duration;
    TextFieldLabel kb;

    int bookId;

    public AddBooks() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(16, 20, 16, 20));


        bookTitle = new TextFieldLabel("Book Title");
        bookAuthor = new TextFieldLabel("Book Author");

        JPanel GroupedInputPanel = new JPanel();
        GroupedInputPanel.setLayout(new BoxLayout(GroupedInputPanel, BoxLayout.X_AXIS));
        GroupedInputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        ISBN = new TextFieldLabel("ISBN");
        price = new TextFieldLabel("Price (ex 0.00)");
        GroupedInputPanel.add(ISBN);
        GroupedInputPanel.add(price);
        GroupedInputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel ComboPanel = new JPanel();
        ComboPanel.setLayout(new BoxLayout(ComboPanel, BoxLayout.Y_AXIS));
        ComboPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        bookType = new JComboBox<>();
        bookType.addItem("Hard Cover");
        bookType.addItem("eBook");
        bookType.addItem("Audio Book");
        bookType.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        bookType.setAlignmentX(Component.LEFT_ALIGNMENT);
        ComboPanel.add(bookType);

        JPanel hardCover = new JPanel();
        hardCover.setLayout(new BoxLayout(hardCover, BoxLayout.X_AXIS));
        hardCover.setAlignmentX(Component.LEFT_ALIGNMENT);
        hardCover.add(pages = new TextFieldLabel("Pages"));
        hardCover.add(stock = new TextFieldLabel("Stock"));

        JPanel eBook = new JPanel();
        eBook.setLayout(new BoxLayout(eBook, BoxLayout.Y_AXIS));
        eBook.setAlignmentX(Component.LEFT_ALIGNMENT);
        eBook.add(kb = new TextFieldLabel("Kb"));

        JPanel audioBook = new JPanel();
        audioBook.setLayout(new BoxLayout(audioBook, BoxLayout.Y_AXIS));
        audioBook.setAlignmentX(Component.LEFT_ALIGNMENT);
        audioBook.add(duration = new TextFieldLabel("Duration (hours)"));

        objectFieldsCardLayout = new CardLayout();

        objectFieldsPanel = new JPanel(objectFieldsCardLayout);
        objectFieldsPanel.add(hardCover, "hardCover");
        objectFieldsPanel.add(eBook, "eBook");
        objectFieldsPanel.add(audioBook, "audioBook");
        objectFieldsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        objectFieldsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        bookType.addActionListener(this);

        addImageButton = new JButton("Choose Image");
        addImageButton.addActionListener(this);
        addImageButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(this);
        addBookButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.add(bookTitle);
        this.add(bookAuthor);
        this.add(GroupedInputPanel);
        this.add(ComboPanel);
        this.add(objectFieldsPanel);
        fileName = new JLabel();
        if (fileName != null) {
            this.add(fileName);
        }
        this.add(addImageButton);
        this.add(addBookButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBookButton) {
            if (bookTitle.getTextInput().trim().isEmpty() ||
                    bookAuthor.getTextInput().trim().isEmpty() ||
                    ISBN.getTextInput().trim().isEmpty() ||
                    price.getTextInput().trim().isEmpty()) {
                switch (bookType.getSelectedItem().toString()) {
                    case "Hard Cover":
                        if (pages.getTextInput().trim().isEmpty() || stock.getTextInput().trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                        }
                        break;
                    case "eBook":
                        if (kb.getTextInput().trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                        }
                        break;
                    case "Audio Book":
                        if (duration.getTextInput().trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                        }
                        break;
                }
                return;
            }
            try {
                Connection conn = db.getConnection();
                String addBook = "INSERT INTO Book (Title, BookImage, Author, Price, ISBN) VALUES (?, ?, ?, ?, ?);";
                PreparedStatement stmt = conn.prepareStatement(addBook);

                stmt.setString(1, bookTitle.getTextInput());
                stmt.setString(2, fileName.getText());
                stmt.setString(3, bookAuthor.getTextInput());
                double priceValue = Double.parseDouble(price.getTextInput().trim());
                stmt.setDouble(4, priceValue);
                int isbn = Integer.parseInt(ISBN.getTextInput());
                stmt.setInt(5, isbn);

                int rowsInserted = stmt.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("Book added successfully!");
                    String bookID = "SELECT BookID FROM Book WHERE Title = ?;";
                    PreparedStatement stm = conn.prepareStatement(bookID);
                    stm.setString(1, bookTitle.getTextInput());

                    ResultSet rs = stm.executeQuery();
                    if (rs.next()) {
                        bookId = rs.getInt("BookID");
                        System.out.println("Found book ID: " + bookId);
                    } else {
                        System.out.println("No book found with that title.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add book.");
                    return;
                }
            } catch (SQLException d) {
                System.out.println("Database error: " + d.getMessage());
                d.printStackTrace();
            }

            switch (bookType.getSelectedItem().toString()) {
                case "Hard Cover":
                    addHardCover();
                    break;
                case "eBook":
                    addEbook();
                    break;
                case "Audio Book":
                    addAudioBook();
                    break;
            }
        }


        if (e.getSource() == addImageButton) {
            JFileChooser imageChooser = new JFileChooser();
            FileNameExtensionFilter imageFilter = new FileNameExtensionFilter(
                    "Image Files (*.jpg, *.png)", "jpg", "jpeg", "png"
            );
            imageChooser.setFileFilter(imageFilter);
            int respnse = imageChooser.showOpenDialog(null);
            if (respnse == JFileChooser.APPROVE_OPTION) {
                File image = imageChooser.getSelectedFile();
                fileName.setText(image.getName());

                File appImagesFolder = new File("src/weeksTasks/week03/images/bookcovers/");

                File destinationFile = new File(appImagesFolder, image.getName());

                try {
                    // Copy file
                    Files.copy(image.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    // Now set the filename (or path) in your UI or database
                    fileName.setText(destinationFile.getPath());  // or just destinationFile.getName()

                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to copy image file.");
                }

            }
        }


        if(e.getSource() == bookType){
            switch (bookType.getSelectedItem().toString()) {
                case "Hard Cover":
                    objectFieldsCardLayout.show(objectFieldsPanel, "hardCover");
                    System.out.println("Hard Cover");
                    break;
                case "eBook":
                    objectFieldsCardLayout.show(objectFieldsPanel, "eBook");
                    System.out.println("eBook");
                    break;
                case "Audio Book":
                    objectFieldsCardLayout.show(objectFieldsPanel, "audioBook");
                    System.out.println("audioBook");
                    break;
            }
        }
    }
    public void addHardCover() {
        try {
            Connection conn = db.getConnection();
            String addPrinted = "INSERT INTO PrintedBook (Pages, Stock, BookID) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(addPrinted);
            int bookPages = Integer.parseInt(pages.getTextInput().trim());
            stmt.setInt(1, bookPages);
            int bookStock = Integer.parseInt(stock.getTextInput().trim());
            stmt.setInt(2, bookStock);
            stmt.setInt(3, bookId);
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Hard cover book added to PrintedBook table.");
            } else {
                System.out.println("Insert failed. No rows affected.");
            }

        } catch (SQLException d) {
            System.out.println("Database error: " + d.getMessage());
            d.printStackTrace();
        }
    }
    public void addEbook() {
        try {
            Connection conn = db.getConnection();
            String addEbook = "INSERT INTO Ebook (Kb, BookID) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(addEbook);
            int bookKb = Integer.parseInt(kb.getTextInput().trim());
            stmt.setInt(1, bookKb);
            stmt.setInt(2, bookId);
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Ebook added to Ebook table.");
            } else {
                System.out.println("Insert failed. No rows affected.");
            }
        } catch (SQLException d) {
            System.out.println("Database error: " + d.getMessage());
            d.printStackTrace();
        }
    }
    public void addAudioBook() {
        try {
            Connection conn = db.getConnection();
            String addAudioBook = "INSERT INTO AudioBook (Duration, BookID) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(addAudioBook);
            int bookDuration = Integer.parseInt(duration.getTextInput().trim());
            stmt.setInt(1, bookDuration);
            stmt.setInt(2, bookId);
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Audio book added to AudiBook table.");
            } else {
                System.out.println("Insert failed. No rows affected.");
            }
        } catch (SQLException d) {
            System.out.println("Database error: " + d.getMessage());
            d.printStackTrace();
        }
    }
}
