package finalAssigments.StoreStorageApp.GUI.Pages.AllProducts;

import finalAssigments.StoreStorageApp.DBConnection;
import finalAssigments.StoreStorageApp.GUI.Components.ImageChooser;
import finalAssigments.StoreStorageApp.GUI.Components.LabelTextInput;
import finalAssigments.StoreStorageApp.GUI.Components.MyButton;
import finalAssigments.StoreStorageApp.GUI.Components.MyComboBox;
import finalAssigments.StoreStorageApp.GUI.Pages.AllProducts.CategoriesInputs.ClothingPanel;
import finalAssigments.StoreStorageApp.GUI.Pages.AllProducts.CategoriesInputs.ElectronicsPanel;
import finalAssigments.StoreStorageApp.GUI.Pages.AllProducts.CategoriesInputs.GroceryPanel;
import finalAssigments.StoreStorageApp.GUI.Pages.AllProducts.CategoriesInputs.ProductCategory;
import finalAssigments.StoreStorageApp.SQLQueries.FindIDUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreateProduct extends JFrame implements ActionListener {

    private MyButton AddNewProduct;
    private LabelTextInput ProductName;
    private LabelTextInput ProductPrice;
    private LabelTextInput ProductSKU;
    private MyComboBox CategorySet;
    private int productID;
    private ProductCategory selectedPanel;
    private ImageChooser chooser;
    private String imageFileName;
    private JLabel productPlaceHolderLabel;


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
        productPlaceHolderLabel = new JLabel(productPlaceHolderIcon);
        MyButton productAddImage = new MyButton("Add Image", "", "0xffffff");
        productAddImage.setActionlistener( e -> {
                chooser = new ImageChooser();
                chooser.setSavePath("src/finalAssigments/StoreStorageApp/images/products");
                chooser.choose(this);
                imageFileName = chooser.getSavedFileName();

                if (imageFileName != null) {
                    ImageIcon newImage = new ImageIcon("src/finalAssigments/StoreStorageApp/images/products/" + imageFileName);
                    Image scaled = newImage.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                    productPlaceHolderLabel.setIcon(new ImageIcon(scaled));
                    productPlaceHolderLabel.setText(null);
                }
        }
        );
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
        selectedPanel = electronicsInputPanel;
        inputsManager.show(categoryInputs, "Electronics");


        CategorySet.setActionListener(e -> {
            String selected = CategorySet.getSelectedItem();
            if ("Electronics".equals(selected)) {
                selectedPanel = electronicsInputPanel;
                inputsManager.show(categoryInputs, "Electronics");
            } else if ("Clothing".equals(selected)) {
                selectedPanel = clothingInputPanel;
                inputsManager.show(categoryInputs, "Clothing");
            } else if ("Grocery".equals(selected)) {
                selectedPanel = groceryInputPanel;
                inputsManager.show(categoryInputs, "Grocery");
            }
        });

        JPanel bottomPanel = new JPanel();
        AddNewProduct = new MyButton("Add Product", "", "0xA081FF");
        AddNewProduct.setAlignmentX(Component.CENTER_ALIGNMENT);
        AddNewProduct.setActionlistener(this);
        bottomPanel.add(AddNewProduct);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(categoryInputs, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == AddNewProduct) {
            try {
                DBConnection db = new DBConnection();
                Connection conn = db.getConnection();

                String createProduct = "INSERT INTO Products (Name, SKU, Price, Image) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(createProduct);

                stmt.setString(1, ProductName.getTextInput());
                stmt.setString(2, ProductSKU.getTextInput());
                /* INSERTING PRICE */
                double price;
                try {
                    price = Double.parseDouble(ProductPrice.getTextInput());
                    stmt.setDouble(3, price);
                } catch (NumberFormatException ex) {
                    ProductPrice.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                    JOptionPane.showMessageDialog(null, "Invalid Price: must be a number 0.00");
                    ex.printStackTrace();
                }
                stmt.setString(4, imageFileName);
                int rowsInserted = stmt.executeUpdate();
                productID = FindIDUtil.findID("ProductID", "Products", "Name", ProductName.getTextInput());
                if (selectedPanel != null) {
                    selectedPanel.insertCategoryData(productID, conn);
                }
                stmt.close();
            } catch (Exception e1) {
                System.out.println("Adding New Product Failed - Unable to connect to database" + e1.getMessage());
                e1.printStackTrace();
            }
        }
    }
}
