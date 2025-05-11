package finalAssigments.StoreStorageApp.GUI.Pages.AllProducts;

import finalAssigments.StoreStorageApp.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ProductRenderer extends JPanel implements ListCellRenderer<Products> {
    private JLabel imageLabel = new JLabel();
    private JLabel nameLabel = new JLabel();
    private JLabel detailLabel = new JLabel();
    private JPanel content = new JPanel();

    public ProductRenderer() {
        setLayout(new BorderLayout());

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        detailLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));

        content.add(nameLabel);
        content.add(detailLabel);
        content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.add(imageLabel, BorderLayout.WEST);
        this.add(content, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Products> list, Products product, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        // Load image based on product
        String path = "src/finalAssigments/StoreStorageApp/images/products/" + product.getImageFileName();
        File file = new File(path);
        ImageIcon icon;

        if (file.exists()) {
            icon = new ImageIcon(path);
        } else {
            icon = new ImageIcon("src/finalAssigments/StoreStorageApp/images/products/productplaceholder.jpg");
        }

        Image scaled = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaled));

        // Set product info
        String title = product.getProductName() + " — " + product.getPrice() + "€";
        String details = "";

        if (product instanceof Electronics e) {
            details = "Brand: " + e.getBrandName() + " | Warranty: " + e.getWarranty() + "y | Stock: " + e.getStock();
        } else if (product instanceof Grocery g) {
            details = "Weight: " + g.getWeight() + "kg | Expires: " + g.getExpirationDate();
        } else if (product instanceof Clothing c) {
            details = "Size: " + c.getSize() + " | Color: " + c.getColor() + " | Material: " + c.getMaterial();
        }

        nameLabel.setText(title);
        detailLabel.setText(details);

        content.setBackground(isSelected ? new Color(210, 230, 255) : Color.WHITE);
        setBackground(isSelected ? new Color(210, 230, 255) : Color.WHITE);

        return this;
    }
}
