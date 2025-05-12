package finalAssigments.StoreStorageApp.GUI.Pages.AllProducts;

import finalAssigments.StoreStorageApp.*;
import finalAssigments.StoreStorageApp.GUI.Components.LabelTextInput;
import finalAssigments.StoreStorageApp.GUI.Components.MyButton;
import finalAssigments.StoreStorageApp.GUI.Components.MyComboBox;
import finalAssigments.StoreStorageApp.GUI.Pages.AllProducts.CategoriesInputs.ClothingPanel;
import finalAssigments.StoreStorageApp.GUI.Pages.AllProducts.CategoriesInputs.ElectronicsPanel;
import finalAssigments.StoreStorageApp.GUI.Pages.AllProducts.CategoriesInputs.GroceryPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Modify extends JFrame implements ActionListener {
    private MyButton AddNewProduct;
    private MyButton DeleteButton;
    private MyButton productAddImage;
    private LabelTextInput ProductName;
    private LabelTextInput ProductPrice;
    private LabelTextInput ProductSKU;
    private MyComboBox CategorySet;

    private ElectronicsPanel electronicsInputPanel;
    private GroceryPanel groceryInputPanel;
    private ClothingPanel clothingInputPanel;

    private String selected;
    private final Products selectedProduct;

    public Modify(JPanel parent, Products selectedProduct) {
        this.selectedProduct = selectedProduct;
        this.setTitle("Modify Product");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 600);
        this.setLocationRelativeTo(parent);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(new EmptyBorder(6, 6, 3, 6));

        JPanel LeftPanel = new JPanel();
        LeftPanel.setLayout(new BoxLayout(LeftPanel, BoxLayout.Y_AXIS));
        ImageIcon productPlaceHolderImage = new ImageIcon("src/finalAssigments/StoreStorageApp/images/products/" + selectedProduct.getImageFileName());
        Image productPlaceHolderScaled = productPlaceHolderImage.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon productPlaceHolderIcon = new ImageIcon(productPlaceHolderScaled);
        JLabel productPlaceHolderLabel = new JLabel(productPlaceHolderIcon);
        productAddImage = new MyButton("Add Image", "", "0xffffff");
        productPlaceHolderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        productAddImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        LeftPanel.add(productPlaceHolderLabel);
        LeftPanel.add(Box.createVerticalStrut(6));
        LeftPanel.add(productAddImage);

        JPanel RightPanel = new JPanel();
        RightPanel.setLayout(new BoxLayout(RightPanel, BoxLayout.Y_AXIS));
        JPanel fillSpace = new JPanel(new GridLayout(1, 1));
        ProductName = new LabelTextInput("Product Name", "", false);
        ProductName.setAlignmentX(Component.LEFT_ALIGNMENT);
        fillSpace.add(ProductName);

        JPanel SideBySidePanel = new JPanel();
        SideBySidePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ProductPrice = new LabelTextInput("Product Price", "", false);
        ProductSKU = new LabelTextInput("Product SKU", "Product store code", false);
        SideBySidePanel.add(ProductPrice);
        SideBySidePanel.add(ProductSKU);

        ArrayList<String> ProductCategories = new ArrayList<>();
        ProductCategories.add("Electronics");
        ProductCategories.add("Clothing");
        ProductCategories.add("Grocery");
        CategorySet = new MyComboBox("Select Product Category", ProductCategories);

        RightPanel.add(fillSpace);
        RightPanel.add(SideBySidePanel);
        RightPanel.add(CategorySet);

        topPanel.add(LeftPanel);
        topPanel.add(RightPanel);

        /* DIFFERENT INPUTS */
        electronicsInputPanel = new ElectronicsPanel();
        clothingInputPanel = new ClothingPanel();
        groceryInputPanel = new GroceryPanel();

        CardLayout inputsManager = new CardLayout();
        JPanel categoryInputs = new JPanel(inputsManager);
        categoryInputs.setLayout(inputsManager);
        categoryInputs.setBorder(new EmptyBorder(10, 10, 10, 10));
        categoryInputs.add(electronicsInputPanel, "Electronics");
        categoryInputs.add(clothingInputPanel, "Clothing");
        categoryInputs.add(groceryInputPanel, "Grocery");

        CategorySet.setActionListener(e -> {
            selected = CategorySet.getSelectedItem();
            if ("Electronics".equals(selected)) {
                inputsManager.show(categoryInputs, "Electronics");
            } else if ("Clothing".equals(selected)) {
                inputsManager.show(categoryInputs, "Clothing");
            } else if ("Grocery".equals(selected)) {
                inputsManager.show(categoryInputs, "Grocery");
            }
        });

        ProductName.setTextInput(selectedProduct.getProductName());
        ProductPrice.setTextInput(String.valueOf(selectedProduct.getPrice()));
        ProductSKU.setTextInput(selectedProduct.getSKU());

        if (selectedProduct instanceof Electronics e) {
            CategorySet.setSelectedItem("Electronics");
            electronicsInputPanel.setData(e);
        } else if (selectedProduct instanceof Clothing c) {
            CategorySet.setSelectedItem("Clothing");
            clothingInputPanel.setData(c);
        } else if (selectedProduct instanceof Grocery g) {
            CategorySet.setSelectedItem("Grocery");
            groceryInputPanel.setData(g);
        }

        JPanel bottomPanel = new JPanel();
        DeleteButton = new MyButton("Delete Product", "", "0xffffff");
        AddNewProduct = new MyButton("Apply Changes", "", "0xA081FF");
        AddNewProduct.setActionlistener(this);
        AddNewProduct.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(DeleteButton);
        bottomPanel.add(AddNewProduct);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(categoryInputs, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == AddNewProduct) {
                DBConnection db = new DBConnection();
                Connection conn = db.getConnection();

                // 1. Update the main Products table
                String updateProduct = "UPDATE Products SET Name = ?, SKU = ?, Price = ? WHERE ProductID = ?";
                PreparedStatement stmt = conn.prepareStatement(updateProduct);
                stmt.setString(1, ProductName.getTextInput());
                stmt.setString(2, ProductSKU.getTextInput());
                stmt.setDouble(3, Double.parseDouble(ProductPrice.getTextInput()));
                stmt.setInt(4, selectedProduct.getId());  // make sure selectedProduct is a class field
                stmt.executeUpdate();
                stmt.close();

                // 2. Update the subcategory table using the correct panel
                String selectedCategory = CategorySet.getSelectedItem();
                switch (selectedCategory) {
                    case "Electronics":
                        electronicsInputPanel.updateCategoryData(selectedProduct.getId(), conn);
                        break;
                    case "Clothing":
                        clothingInputPanel.updateCategoryData(selectedProduct.getId(), conn);
                        break;
                    case "Grocery":
                        groceryInputPanel.updateCategoryData(selectedProduct.getId(), conn);
                        break;
                }

                JOptionPane.showMessageDialog(this, "Product updated successfully!");
                this.dispose(); // Close the window after update
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to update product: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
