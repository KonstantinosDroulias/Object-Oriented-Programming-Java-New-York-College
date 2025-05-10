package finalAssigments.StoreStorageApp.GUI.Pages.AllProducts.CategoriesInputs;

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

    @Override
    public void insertCategoryData(int productID, Connection conn) throws SQLException {
        String ClothingQuery = "INSERT INTO Clothing (ProductID, MaterialID) VALUES (?, ?, ?)";
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

}
