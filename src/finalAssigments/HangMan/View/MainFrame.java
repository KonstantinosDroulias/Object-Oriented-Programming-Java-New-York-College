package finalAssigments.HangMan.View;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public MainFrame() {
        this.setTitle("Hang Man Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        //cardPanel.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        this.add(cardPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void addView(JPanel view, String name) {
        cardPanel.add(view, name);
    }

    public void showView(String name) {
        cardLayout.show(cardPanel, name);
    }
}
