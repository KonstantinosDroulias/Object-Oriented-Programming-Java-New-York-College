package finalAssigments.StoreStorageApp.GUI.Pages.AllProducts;

import finalAssigments.StoreStorageApp.DBConnection;
import finalAssigments.StoreStorageApp.GUI.Components.LabelTextInput;
import finalAssigments.StoreStorageApp.GUI.Components.MyButton;
import finalAssigments.StoreStorageApp.Products;
import finalAssigments.StoreStorageApp.ProductsListLoader;
import finalAssigments.StoreStorageApp.SQLQueries.ArrayFillUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class AllProducts extends JPanel {

    public AllProducts() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("All Products"));

        DBConnection db = new DBConnection();
        Connection conn = db.getConnection();

        /* Center Panel */
        JPanel productsPanel = new JPanel();
        productsPanel.setBorder(new EmptyBorder(12, 12, 12, 6));
        productsPanel.setLayout(new BoxLayout(productsPanel, BoxLayout.Y_AXIS));
        productsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        DefaultListModel<Products> productModel = new DefaultListModel<>();
        ArrayList<Products> products = ProductsListLoader.loadAllProducts(conn);

        for (Products p : products) {
            productModel.addElement(p);
        }

        JList<Products> productsList = new JList<>(productModel);
        productsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productsList.setCellRenderer(new ProductRenderer());

        productsPanel.add(new JScrollPane(productsList));

        /* Functions Buttons */
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(new EmptyBorder(12, 6, 12, 12));
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        LabelTextInput searchProduct = new LabelTextInput("Search Product", "", true);
        searchProduct.setActionListener(e -> {
            new Search(searchProduct.getTextInput().trim(), productModel, products);
        });

        MyButton AddNewProduct = new MyButton("Add New Product", "", "0xA081FF");
        AddNewProduct.addActionListener(e -> {new CreateProduct(this);});
        MyButton Modify = new MyButton("Modify Product", "", "0xFFFFFF");
        Modify.addActionListener(e -> {
            Products selectedProduct = productsList.getSelectedValue();
            if (selectedProduct != null) {
                new Modify(this, selectedProduct);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a product to modify.");
            }
        });

        MyButton Refresh = new MyButton("Refresh", "", "0xFFFFFF");
        Refresh.setActionlistener(e -> {
            try {
                productModel.clear(); // clear old list

                ArrayList<Products> newProducts = ProductsListLoader.loadAllProducts(conn); // fresh list
                for (Products p : newProducts) {
                    productModel.addElement(p);
                }

                productsList.revalidate(); // repaint and layout update
                productsList.repaint();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to refresh product list.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonsPanel.add(searchProduct);
        buttonsPanel.add(Box.createVerticalStrut(10));
        buttonsPanel.add(AddNewProduct);
        buttonsPanel.add(Box.createVerticalStrut(10));
        buttonsPanel.add(Modify);
        buttonsPanel.add(Box.createVerticalStrut(10));
        buttonsPanel.add(Refresh);
        buttonsPanel.add(Box.createVerticalGlue());

        this.add(productsPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.EAST);
    }
}
