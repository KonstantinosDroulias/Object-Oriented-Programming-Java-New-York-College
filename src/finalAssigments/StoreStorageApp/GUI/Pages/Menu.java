package finalAssigments.StoreStorageApp.GUI.Pages;

import finalAssigments.StoreStorageApp.GUI.Components.MyButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Menu extends JPanel {

    public Menu(CardLayout cardLayout, JPanel pageContent) {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(8, 8, 8, 8));
        this.setBackground(new Color(0xA081FF));
        this.setOpaque(true);

        /* Logo Panel */
        JPanel logoPanel = new JPanel();
        logoPanel.setOpaque(false);
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.Y_AXIS));
        ImageIcon StoreLogoPlacement = new ImageIcon("src/finalAssigments/StoreStorageApp/images/store/logohere.png");
        Image StoreLogoPlacementScaled = StoreLogoPlacement.getImage().getScaledInstance(160, 60, Image.SCALE_SMOOTH);
        ImageIcon StoreLogo = new ImageIcon(StoreLogoPlacementScaled);
        JLabel StoreLabel = new JLabel(StoreLogo);
        logoPanel.add(StoreLabel);

        /* Nav Panel */
        JPanel navigationPanel = new JPanel();
        navigationPanel.setOpaque(false);
        navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.Y_AXIS));
        MyButton AllProductsButton = new MyButton("All Products", "0xffffff", "");
        AllProductsButton.addActionListener(e -> {
            cardLayout.show(pageContent, "All Products");
        });
        MyButton attributesButton = new MyButton("Attributes", "0xffffff", "");
        attributesButton.addActionListener(e -> {
            cardLayout.show(pageContent, "Attributes");
        });
        MyButton StoreSettingsButton = new MyButton("Store Settings", "0xffffff", "");
        StoreSettingsButton.addActionListener(e -> cardLayout.show(pageContent, "Store Settings"));
        navigationPanel.add(Box.createVerticalGlue());
        navigationPanel.add(AllProductsButton);
        navigationPanel.add(Box.createVerticalStrut(10));
        navigationPanel.add(attributesButton);
        navigationPanel.add(Box.createVerticalStrut(10));
        navigationPanel.add(StoreSettingsButton);
        navigationPanel.add(Box.createVerticalGlue());

        /* Logout Panel */
        JPanel logoutPanel = new JPanel();
        logoutPanel.setOpaque(false);
        logoutPanel.setLayout(new BoxLayout(logoutPanel, BoxLayout.Y_AXIS));
        MyButton LogoutButton = new MyButton("Logout", "0xFFFFFF", "0xFF2D30");
        logoutPanel.add(LogoutButton);

        this.add(logoPanel, BorderLayout.NORTH);
        this.add(navigationPanel, BorderLayout.CENTER);
        this.add(logoutPanel, BorderLayout.SOUTH);
    }
}
