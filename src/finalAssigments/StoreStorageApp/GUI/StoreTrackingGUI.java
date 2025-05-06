package finalAssigments.StoreStorageApp.GUI;

import finalAssigments.StoreStorageApp.GUI.Pages.AllProducts.AllProducts;
import finalAssigments.StoreStorageApp.GUI.Pages.Attributes.Attributes;
import finalAssigments.StoreStorageApp.GUI.Pages.Menu;
import finalAssigments.StoreStorageApp.GUI.Pages.StoreSettings.StoreSettings;
import org.w3c.dom.Attr;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StoreTrackingGUI extends JFrame {
    CardLayout cardLayout;
    JPanel pageContent;

    public StoreTrackingGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("All Products");
        this.setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        pageContent = new JPanel(cardLayout);

        AllProducts allProducts = new AllProducts();
        StoreSettings storeSettings = new StoreSettings();
        Attributes attributes = new Attributes();

        pageContent.add(allProducts, "All Products");
        pageContent.add(attributes, "Attributes");
        pageContent.add(storeSettings, "Store Settings");

        this.add(new Menu(cardLayout, pageContent), BorderLayout.WEST);
        this.add(pageContent, BorderLayout.CENTER);

        this.setSize(800, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
