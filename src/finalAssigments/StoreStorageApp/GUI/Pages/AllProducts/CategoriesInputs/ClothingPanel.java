package finalAssigments.StoreStorageApp.GUI.Pages.AllProducts.CategoriesInputs;

import finalAssigments.StoreStorageApp.Clothing;
import finalAssigments.StoreStorageApp.DBConnection;
import finalAssigments.StoreStorageApp.GUI.Components.MultiSelection;
import finalAssigments.StoreStorageApp.GUI.Components.MyComboBox;
import finalAssigments.StoreStorageApp.SQLQueries.ArrayFillUtil;
import finalAssigments.StoreStorageApp.SQLQueries.FindIDUtil;
import finalAssigments.StoreStorageApp.SQLQueries.JoinTableUtil;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClothingPanel extends JPanel implements ProductCategory {
    private MyComboBox materialSelection;
    private MultiSelection sizesSelection;
    private MultiSelection colorsSelection;
    private ArrayList<String> clothingTypes;
    private ArrayList<String> clothingMaterials;
    private ArrayList<String> clothingColors;

    public ClothingPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            DBConnection db = new DBConnection();
            Connection conn = db.getConnection();

            sizesSelection = new MultiSelection(ArrayFillUtil.fillArray(new ArrayList<>(), conn, "Size", "Size"));

            materialSelection = new MyComboBox("Product Material", clothingMaterials = ArrayFillUtil.fillArray(new ArrayList<>(), conn, "Material", "Material"));

            colorsSelection = new MultiSelection(clothingColors = ArrayFillUtil.fillArray(new ArrayList<>(), conn, "Color", "Colors"));

        sizesSelection.setAlignmentX(Component.LEFT_ALIGNMENT);
        materialSelection.setAlignmentX(Component.LEFT_ALIGNMENT);
        colorsSelection.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.add(sizesSelection);
        this.add((Box.createVerticalStrut(10)));
        this.add(materialSelection);
        this.add((Box.createVerticalStrut(10)));
        this.add(colorsSelection);
        this.add(Box.createVerticalGlue());
    }

    public void setData(Clothing product) {
        materialSelection.setSelectedItem(product.getMaterial());
        sizesSelection.setSelectedItems(product.getSize());
        colorsSelection.setSelectedItems(product.getColor());
    }


    @Override
    public void insertCategoryData(int productID, Connection conn) throws SQLException {
        String ClothingQuery = "INSERT INTO Clothing (ProductID, MaterialID) VALUES (?, ?)";
        PreparedStatement queryData = conn.prepareStatement(ClothingQuery);
        queryData.setInt(1, productID);
        String material = materialSelection.getSelectedItem();
        int materialID = FindIDUtil.findID("MaterialID", "Material", "Material", material);
        queryData.setInt(2, materialID);
        int rowInserted = queryData.executeUpdate();
        /* I could hard code the first values, but I wanted to be feature proof so in case we want to store data that require this query we don't need to change anything */
        for (String size : sizesSelection.getSelectedItems()) {
            JoinTableUtil.insertJoin(conn, "ClothingSizes", "ClothingID", "Clothing", "ProductID", productID, "SizeID", "Size", "Size", size);
        }
        for (String color : colorsSelection.getSelectedItems()) {
            JoinTableUtil.insertJoin(conn, "ClothingColors", "ClothingID", "Clothing", "ProductID", productID, "ColorID", "Colors", "Color", color);
        }
        queryData.close();
    }

    @Override
    public void updateCategoryData(int productId, Connection conn) {
        try {
            String updateQuery = "UPDATE Clothing SET MaterialID = ? WHERE ProductID = ?";
            PreparedStatement stmt = conn.prepareStatement(updateQuery);
            int materialId = FindIDUtil.findID("MaterialID", "Material", "Material", materialSelection.getSelectedItem());
            stmt.setInt(1, materialId);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
            stmt.close();

            // I couldn't think of a way to do this so I asked ai to help me out. Apparently I need to learn join tables...
            JoinTableUtil.clearJoins(conn, "ClothingSizes", "ClothingID", "Clothing", "ProductID", productId);
            for (String size : sizesSelection.getSelectedItems()) {
                JoinTableUtil.insertJoin(conn, "ClothingSizes", "ClothingID", "Clothing", "ProductID", productId, "SizeID", "Size", "Size", size);
            }

            JoinTableUtil.clearJoins(conn, "ClothingColors", "ClothingID", "Clothing", "ProductID", productId);
            for (String color : colorsSelection.getSelectedItems()) {
                JoinTableUtil.insertJoin(conn, "ClothingColors", "ClothingID", "Clothing", "ProductID", productId, "ColorID", "Colors", "Color", color);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to update clothing info.", "Update Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
