package finalAssigments.StoreStorageApp.GUI.Pages.Attributes;

import finalAssigments.StoreStorageApp.DBConnection;
import finalAssigments.StoreStorageApp.GUI.Components.LabelTextInput;
import finalAssigments.StoreStorageApp.SQLQueries.ArrayFillUtil;
import finalAssigments.StoreStorageApp.SQLQueries.QuickAddUtil;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Attributes extends JPanel {

    public Attributes() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        String addIcon = "plus.png";

        DBConnection db = new DBConnection();
        Connection conn = db.getConnection();

        QuickAddUtil add = new QuickAddUtil();

        JPanel electronicsPanel = new JPanel();
        electronicsPanel.setBorder(BorderFactory.createTitledBorder("Electronics"));
        electronicsPanel.setLayout(new BoxLayout(electronicsPanel, BoxLayout.Y_AXIS));

        LabelTextInput Brands = new LabelTextInput("Add Product Brand", "Add product brands for electronics products", true);
        Brands.setImageIcon(addIcon);

        DefaultListModel<String> brandModel = new DefaultListModel<>();
        ArrayList<String> brands = ArrayFillUtil.fillArray(new ArrayList<>(), conn, "BrandName", "Brands");
        Brands.setActionListener(e -> {
            String trimedBrand = Brands.getTextInput().trim();
            if (!trimedBrand.isEmpty()) {
                add.addValue(conn, "Brands", "BrandName", trimedBrand);
                brandModel.clear();
                ArrayList<String> updatedSizes = ArrayFillUtil.fillArray(new ArrayList<>(), conn, "BrandName", "Brands");
                for (String s : updatedSizes) {
                    brandModel.addElement(s);
                }
            }
        });
        for (String brand : brands) {
            brandModel.addElement(brand);
        }

        JList<String> brandsList = new JList<>(brandModel);
        brandsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        electronicsPanel.add(Brands);
        electronicsPanel.add(Box.createVerticalStrut(8));
        electronicsPanel.add(new JScrollPane(brandsList));

        JPanel clothingPanel = new JPanel();
        clothingPanel.setBorder(BorderFactory.createTitledBorder("Clothing"));
        clothingPanel.setLayout(new BoxLayout(clothingPanel, BoxLayout.Y_AXIS));

        JPanel sizesPanel = new JPanel(new BorderLayout(10, 0));
        LabelTextInput Sizes = new LabelTextInput("Add Product Sizes", "Add product sizes for clothing products", true);
        Sizes.setImageIcon(addIcon);

        DefaultListModel<String> sizeModel = new DefaultListModel<>();
        ArrayList<String> sizes = ArrayFillUtil.fillArray(new ArrayList<>(), conn, "Size", "Size");
        Sizes.setActionListener(e -> {
            String trimedSize = Sizes.getTextInput().trim();
            if (!trimedSize.isEmpty()) {
                add.addValue(conn, "Size", "Size", trimedSize);
                sizeModel.clear();
                ArrayList<String> updatedSizes = ArrayFillUtil.fillArray(new ArrayList<>(), conn, "Size", "Size");
                for (String s : updatedSizes) {
                    sizeModel.addElement(s);
                }
            }
        });
        for (String size : sizes) {
            sizeModel.addElement(size);
        }

        JList<String> sizesList = new JList<>(sizeModel);
        sizesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        sizesPanel.add(Sizes, BorderLayout.WEST);
        sizesPanel.add(new JScrollPane(sizesList), BorderLayout.CENTER);
        clothingPanel.add(sizesPanel);
        clothingPanel.add(Box.createVerticalStrut(12));

        JPanel materialsPanel = new JPanel(new BorderLayout(10, 0));
        LabelTextInput Materials = new LabelTextInput("Add Product Materials", "Add product materials for clothing products", true);
        Materials.setImageIcon(addIcon);

        DefaultListModel<String> materialModel = new DefaultListModel<>();
        ArrayList<String> materials = ArrayFillUtil.fillArray(new ArrayList<>(), conn, "Material", "Material");
        Materials.setActionListener(e -> {
            String trimedMaterial = Materials.getTextInput().trim();
            if (!trimedMaterial.isEmpty()) {
                add.addValue(conn, "Material", "Material", trimedMaterial);
                materialModel.clear();
                ArrayList<String> updatedSizes = ArrayFillUtil.fillArray(new ArrayList<>(), conn, "Material", "Material");
                for (String s : updatedSizes) {
                    materialModel.addElement(s);
                }
            }
        });
        for (String material : materials) {
            materialModel.addElement(material);
        }

        JList<String> materialsList = new JList<>(materialModel);
        materialsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        materialsPanel.add(Materials, BorderLayout.WEST);
        materialsPanel.add(new JScrollPane(materialsList), BorderLayout.CENTER);
        clothingPanel.add(materialsPanel);
        clothingPanel.add(Box.createVerticalStrut(12));

        JPanel colorPanel = new JPanel(new BorderLayout(10, 0));

        JPanel colorInputs = new JPanel(new GridLayout(1, 2, 10, 0));
        LabelTextInput NameColor = new LabelTextInput("Product Color Name", "Add the naming of the product color ex 'pink', 'red' etc.", false);
        LabelTextInput HexColor = new LabelTextInput("Product Color Hex", "Add the hex color of the product ex 'ffffff', '000000' etc", true);
        HexColor.setImageIcon(addIcon);
        colorInputs.add(NameColor);
        colorInputs.add(HexColor);

        DefaultListModel<String> colorsModel = new DefaultListModel<>();
        ArrayList<String> colors = ArrayFillUtil.fillArray(new ArrayList<>(), conn, "Color", "Colors");
        HexColor.setActionListener(e -> {
            String trimedColor = NameColor.getTextInput().trim();
            String trimedHexColor = HexColor.getTextInput().trim();
            if (!trimedColor.isEmpty() && !trimedHexColor.isEmpty()) {
               try {
                   String query = "INSERT INTO Colors (Color, HexValue) VALUES (?, ?)";
                   PreparedStatement stmt = conn.prepareStatement(query);
                   stmt.setString(1, trimedColor);
                   stmt.setString(2, trimedHexColor);
                   int rowsInserted = stmt.executeUpdate();
                   stmt.close();
               } catch (SQLException s) {
                   JOptionPane.showMessageDialog(null, "Error while inserting the color '" + trimedColor + "'");
                   s.printStackTrace();
               }

                colorsModel.clear();
                ArrayList<String> updatedSizes = ArrayFillUtil.fillArray(new ArrayList<>(), conn, "Color", "Colors");
                for (String s : updatedSizes) {
                    colorsModel.addElement(s);
                }
            }
        });
        for (String color : colors) {
            colorsModel.addElement(color);
        }
        JList<String> colorsList = new JList<>(colorsModel);
        colorsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        colorPanel.add(colorInputs, BorderLayout.WEST);
        colorPanel.add(new JScrollPane(colorsList), BorderLayout.CENTER);
        clothingPanel.add(colorPanel);


        this.add(electronicsPanel);
        this.add(Box.createVerticalStrut(4));
        this.add(clothingPanel);
    }
}
