package finalAssigments.StoreStorageApp.GUI.Pages.AllProducts;

import finalAssigments.StoreStorageApp.GUI.Components.LabelTextInput;
import finalAssigments.StoreStorageApp.GUI.Components.MyButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AllProducts extends JPanel {

    public AllProducts() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("All Products"));

        /* Center Panel */
        JPanel productsPanel = new JPanel();
        productsPanel.setBorder(new EmptyBorder(12, 12, 12, 6));
        productsPanel.setLayout(new BoxLayout(productsPanel, BoxLayout.Y_AXIS));
        productsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> productsList = new JList<String>(model);
        productsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        productsPanel.add(new JScrollPane(productsList));

        /* Functions Buttons */
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(new EmptyBorder(12, 6, 12, 12));
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        LabelTextInput searchProduct = new LabelTextInput("Search Product", "Search by name or SKU", true);
        MyButton AddNewProduct = new MyButton("Add New Product", "", "0xA081FF");
        AddNewProduct.addActionListener(e -> {new CreateProduct(this);});
        MyButton Modify = new MyButton("Modify Product", "", "0xFFFFFF");
        Modify.addActionListener(e -> {new Modify(this);});
        MyButton Refresh = new MyButton("Refresh", "", "0xFFFFFF");
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
