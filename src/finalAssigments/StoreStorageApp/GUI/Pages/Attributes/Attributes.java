package finalAssigments.StoreStorageApp.GUI.Pages.Attributes;

import finalAssigments.StoreStorageApp.GUI.Components.LabelTextInput;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Attributes extends JPanel {

    public Attributes() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6)); // outer padding

        JPanel electronicsPanel = new JPanel();
        electronicsPanel.setBorder(BorderFactory.createTitledBorder("Electronics"));
        electronicsPanel.setLayout(new BoxLayout(electronicsPanel, BoxLayout.Y_AXIS));

        LabelTextInput Brands = new LabelTextInput("Add Product Brand", "Add product brands for electronics products", true);
        Brands.setImageIcon("plus.png");

        DefaultListModel<String> brand = new DefaultListModel<>();
        JList<String> brandsList = new JList<>(brand);
        brandsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        electronicsPanel.add(Brands);
        electronicsPanel.add(Box.createVerticalStrut(8));
        electronicsPanel.add(new JScrollPane(brandsList));

        JPanel clothingPanel = new JPanel();
        clothingPanel.setBorder(BorderFactory.createTitledBorder("Clothing"));
        clothingPanel.setLayout(new BoxLayout(clothingPanel, BoxLayout.Y_AXIS));

        JPanel sizesPanel = new JPanel(new BorderLayout(10, 0));
        LabelTextInput Sizes = new LabelTextInput("Add Product Sizes", "Add product sizes for clothing products", true);
        Sizes.setImageIcon("plus.png");

        DefaultListModel<String> size = new DefaultListModel<>();
        JList<String> sizesList = new JList<>(size);
        sizesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        sizesPanel.add(Sizes, BorderLayout.WEST);
        sizesPanel.add(new JScrollPane(sizesList), BorderLayout.CENTER);
        clothingPanel.add(sizesPanel);
        clothingPanel.add(Box.createVerticalStrut(12));

        JPanel materialsPanel = new JPanel(new BorderLayout(10, 0));
        LabelTextInput Materials = new LabelTextInput("Add Product Materials", "Add product materials for clothing products", true);
        Materials.setImageIcon("plus.png");

        DefaultListModel<String> material = new DefaultListModel<>();
        JList<String> materialsList = new JList<>(material);
        materialsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        materialsPanel.add(Materials, BorderLayout.WEST);
        materialsPanel.add(new JScrollPane(materialsList), BorderLayout.CENTER);
        clothingPanel.add(materialsPanel);
        clothingPanel.add(Box.createVerticalStrut(12));

        JPanel colorPanel = new JPanel(new BorderLayout(10, 0));

        JPanel colorInputs = new JPanel(new GridLayout(1, 2, 10, 0));
        LabelTextInput NameColor = new LabelTextInput("Product Color Name", "Add the naming of the product color ex 'pink', 'red' etc.", false);
        LabelTextInput HexColor = new LabelTextInput("Product Color Hex", "Add the hex color of the product ex 'ffffff', '000000' etc", true);
        HexColor.setImageIcon("plus.png");
        colorInputs.add(NameColor);
        colorInputs.add(HexColor);

        DefaultListModel<String> color = new DefaultListModel<>();
        JList<String> colorsList = new JList<>(color);
        colorsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        colorPanel.add(colorInputs, BorderLayout.WEST);
        colorPanel.add(new JScrollPane(colorsList), BorderLayout.CENTER);
        clothingPanel.add(colorPanel);


        this.add(electronicsPanel);
        this.add(Box.createVerticalStrut(4));
        this.add(clothingPanel);
    }
}
