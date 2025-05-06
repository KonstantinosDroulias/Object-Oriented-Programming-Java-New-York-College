package finalAssigments.StoreStorageApp.GUI.Pages.AllProducts;

import finalAssigments.StoreStorageApp.GUI.Components.LabelTextInput;
import finalAssigments.StoreStorageApp.GUI.Components.MyButton;
import finalAssigments.StoreStorageApp.GUI.Components.MyComboBox;
import finalAssigments.StoreStorageApp.GUI.Pages.AllProducts.CategoriesInputs.ClothingPanel;
import finalAssigments.StoreStorageApp.GUI.Pages.AllProducts.CategoriesInputs.ElectronicsPanel;
import finalAssigments.StoreStorageApp.GUI.Pages.AllProducts.CategoriesInputs.GroceryPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class CreateProduct extends JFrame {

    public CreateProduct(JPanel parent){
        this.setTitle("Add New Product");
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
        ImageIcon productPlaceHolderImage = new ImageIcon("src/finalAssigments/StoreStorageApp/images/products/productplaceholder.jpg");
        Image productPlaceHolderScaled = productPlaceHolderImage.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon productPlaceHolderIcon = new ImageIcon(productPlaceHolderScaled);
        JLabel productPlaceHolderLabel = new JLabel(productPlaceHolderIcon);
        MyButton productAddImage = new MyButton("Add Image", "", "0xffffff");
        productPlaceHolderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        productAddImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        LeftPanel.add(productPlaceHolderLabel);
        LeftPanel.add(Box.createVerticalStrut(6));
        LeftPanel.add(productAddImage);

        JPanel RightPanel = new JPanel();
        RightPanel.setLayout(new BoxLayout(RightPanel, BoxLayout.Y_AXIS));
        JPanel fillSpace = new JPanel(new GridLayout(1, 1));
        LabelTextInput ProductName = new LabelTextInput("Product Name", "", false);
        ProductName.setAlignmentX(Component.LEFT_ALIGNMENT);
        fillSpace.add(ProductName);

        JPanel SideBySidePanel = new JPanel();
        SideBySidePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        LabelTextInput ProductPrice = new LabelTextInput("Product Price", "", false);
        LabelTextInput ProductSKU = new LabelTextInput("Product SKU", "Product store code", false);
        SideBySidePanel.add(ProductPrice);
        SideBySidePanel.add(ProductSKU);

        ArrayList<String> ProductCategories = new ArrayList<>();
        ProductCategories.add("Electronics");
        ProductCategories.add("Clothing");
        ProductCategories.add("Grocery");
        MyComboBox CategorySet = new MyComboBox("Select Product Category", ProductCategories);

        RightPanel.add(fillSpace);
        RightPanel.add(SideBySidePanel);
        RightPanel.add(CategorySet);

        topPanel.add(LeftPanel);
        topPanel.add(RightPanel);

        /* DIFFERENT INPUTS */
        ElectronicsPanel electronicsInputPanel = new ElectronicsPanel();
        ClothingPanel clothingInputPanel = new ClothingPanel();
        GroceryPanel groceryInputPanel = new GroceryPanel();

        CardLayout inputsManager = new CardLayout();
        JPanel categoryInputs = new JPanel(inputsManager);
        categoryInputs.setLayout(inputsManager);
        categoryInputs.setBorder(new EmptyBorder(10, 10, 10, 10));
        categoryInputs.add(electronicsInputPanel, "Electronics");
        categoryInputs.add(clothingInputPanel, "Clothing");
        categoryInputs.add(groceryInputPanel, "Grocery");

        CategorySet.setActionListener(e -> {
            String selected = CategorySet.getSelectedItem();
            if ("Electronics".equals(selected)) {
                inputsManager.show(categoryInputs, "Electronics");
            } else if ("Clothing".equals(selected)) {
                inputsManager.show(categoryInputs, "Clothing");
            } else if ("Grocery".equals(selected)) {
                inputsManager.show(categoryInputs, "Grocery");
            }
        });

        JPanel bottomPanel = new JPanel();
        MyButton AddNewProduct = new MyButton("Add Product", "", "0xA081FF");
        AddNewProduct.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(AddNewProduct);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(categoryInputs, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }


}
