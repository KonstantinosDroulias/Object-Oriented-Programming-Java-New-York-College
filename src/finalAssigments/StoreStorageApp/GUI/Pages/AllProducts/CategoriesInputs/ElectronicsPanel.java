package finalAssigments.StoreStorageApp.GUI.Pages.AllProducts.CategoriesInputs;

import finalAssigments.StoreStorageApp.DBConnection;
import finalAssigments.StoreStorageApp.Electronics;
import finalAssigments.StoreStorageApp.GUI.Components.LabelTextInput;
import finalAssigments.StoreStorageApp.GUI.Components.MyComboBox;
import finalAssigments.StoreStorageApp.SQLQueries.ArrayFillUtil;
import finalAssigments.StoreStorageApp.SQLQueries.FindIDUtil;


import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ElectronicsPanel extends JPanel implements ProductCategory {
    private MyComboBox brandsSelection;
    private LabelTextInput stockInput;
    private LabelTextInput warrantyPeriodInput;

    public ElectronicsPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        DBConnection db = new DBConnection();
        Connection conn = db.getConnection();

        brandsSelection = new MyComboBox("Select Brand", ArrayFillUtil.fillArray(new ArrayList<>(), conn, "BrandName", "Brands"));
        warrantyPeriodInput = new LabelTextInput("Warranty period", "Give the Number of Years", false);
        stockInput = new LabelTextInput("Stock", "", false);

        brandsSelection.setAlignmentX(Component.LEFT_ALIGNMENT);
        warrantyPeriodInput.setAlignmentX(Component.LEFT_ALIGNMENT);
        stockInput.setAlignmentX(Component.LEFT_ALIGNMENT);


        this.add(brandsSelection);
        this.add((Box.createVerticalStrut(10)));
        this.add(warrantyPeriodInput);
        this.add((Box.createVerticalStrut(10)));
        this.add(stockInput);
        this.add(Box.createVerticalGlue());
    }

    public void setData(Electronics product) {
        brandsSelection.setSelectedItem(product.getBrandName());
        stockInput.setTextInput(String.valueOf(product.getStock()));
        warrantyPeriodInput.setTextInput(String.valueOf(product.getWarranty()));
    }



    @Override
    public void insertCategoryData(int productID, Connection conn) throws SQLException {
        String electronicsData = "INSERT INTO Electronics (ProductID, BrandID, Stock, WarrantyPeriod) VALUES (?, ?,?,?)";
        PreparedStatement electronics = conn.prepareStatement(electronicsData);
        electronics.setInt(1, productID);
        String brand = brandsSelection.getSelectedItem();
        int brandID = FindIDUtil.findID("BrandID", "Brands", "BrandName", brand);
        electronics.setInt(2, brandID);
        String stockText = stockInput.getTextInput().trim();
        if (stockText.isEmpty()) return;

        int stock = Integer.parseInt(stockText);
        electronics.setInt(3, stock);
        int warrantyPeriod = Integer.parseInt(warrantyPeriodInput.getTextInput());
        electronics.setInt(4, warrantyPeriod);
        int rowInserted = electronics.executeUpdate();
        electronics.close();
    }

    @Override
    public void updateCategoryData(int productId, Connection conn) {
        try {
            String updateQuery = "UPDATE Electronics SET BrandID = ?, Stock = ?, WarrantyPeriod = ? WHERE ProductID = ?";
            PreparedStatement stmt = conn.prepareStatement(updateQuery);
            String brand = brandsSelection.getSelectedItem();
            int brandId = FindIDUtil.findID("BrandID", "Brands", "BrandName", brand);
            int stock = Integer.parseInt(stockInput.getTextInput().trim());
            int warranty = Integer.parseInt(warrantyPeriodInput.getTextInput().trim());

            stmt.setInt(1, brandId);
            stmt.setInt(2, stock);
            stmt.setInt(3, warranty);
            stmt.setInt(4, productId);

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to update electronics info.", "Update Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
