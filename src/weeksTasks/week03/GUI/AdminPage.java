package weeksTasks.week03.GUI;

import weeksTasks.week03.GUI.AdminPagePanels.AddBooks;
import weeksTasks.week03.GUI.AdminPagePanels.ManageBooks;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

public class AdminPage extends JFrame {
    CardLayout cardLayout;
    JPanel cardPanel;
    JComboBox<String> bookType;

    public AdminPage() {
        this.setTitle("Admin Page");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 600);
        this.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu manageBooks = new JMenu("Manage Books");
        JMenu addBook = new JMenu("Add Book");
        menuBar.add(manageBooks);
        menuBar.add(addBook);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        ManageBooks manageBook = new ManageBooks();
        AddBooks AddBook = new AddBooks();

        cardPanel.add(manageBook, "Manage");
        cardPanel.add(AddBook, "Add");

        this.add(cardPanel, BorderLayout.CENTER);

        cardLayout.show(cardPanel, "Manage");

        manageBooks.addMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent e) {
                cardLayout.show(cardPanel, "Manage");
            }
            public void menuDeselected(MenuEvent e) {}
            public void menuCanceled(MenuEvent e) {}
        });

        addBook.addMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent e) {
                cardLayout.show(cardPanel, "Add");
            }
            public void menuDeselected(MenuEvent e) {}
            public void menuCanceled(MenuEvent e) {}
        });



    }
}
