package finalAssigments.StoreStorageApp.GUI.Pages.AllProducts.CategoriesInputs;

import finalAssigments.StoreStorageApp.Clothing;
import finalAssigments.StoreStorageApp.GUI.Components.LabelTextInput;
import finalAssigments.StoreStorageApp.Grocery;
import finalAssigments.StoreStorageApp.SQLQueries.FindIDUtil;
import finalAssigments.StoreStorageApp.SQLQueries.JoinTableUtil;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

import static finalAssigments.StoreStorageApp.GUI.Pages.StoreSettings.StoreSettings.storeWeightMetric;


public class GroceryPanel extends JPanel implements ProductCategory {
    LabelTextInput weightInput;
    LabelTextInput expirationDate;

    public GroceryPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        weightInput = new LabelTextInput("Enter Stock in Weight " + storeWeightMetric, "", false);
        expirationDate = new LabelTextInput("Enter Expiration Date", "Input format must be yyyy-mm-dd", false);
        this.add(weightInput);
        this.add((Box.createVerticalStrut(10)));
        this.add(expirationDate);
        this.add(Box.createVerticalGlue());
    }

    public void setData(Grocery product) {
        weightInput.setTextInput(String.valueOf(product.getWeight()));
        expirationDate.setTextInput(product.getExpirationDate().toString());
    }



    @Override
    public void insertCategoryData(int productID, Connection conn) throws SQLException {
        String GroceryQuery = "INSERT INTO Groceries (ProductID, WeightStock, ExpirationDate) VALUES (?, ?, ?)";
        PreparedStatement queryData = conn.prepareStatement(GroceryQuery);
        queryData.setInt(1, productID);

        try {
            double weight = Double.parseDouble(weightInput.getTextInput());
            queryData.setDouble(2, weight);
        } catch (NumberFormatException e) {
            weightInput.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            JOptionPane.showMessageDialog(null, "Invalid weight: must be a number (e.g., 3.5)", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            java.sql.Date sqlDate = java.sql.Date.valueOf(expirationDate.getTextInput().trim());
            queryData.setDate(3, sqlDate);
        } catch (IllegalArgumentException e) {
            expirationDate.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            JOptionPane.showMessageDialog(null, "Invalid date: use yyyy-MM-dd (e.g., 2025-05-10)", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int rowInserted = queryData.executeUpdate();
        System.out.println("Row inserted: " + rowInserted);


        queryData.close();
    }

    @Override
    public void updateCategoryData(int productId, Connection conn) {
        try {
            String updateQuery = "UPDATE Groceries SET WeightStock = ?, ExpirationDate = ? WHERE ProductID = ?";
            PreparedStatement stmt = conn.prepareStatement(updateQuery);

            double weight = Double.parseDouble(weightInput.getTextInput().trim());
            Date date = Date.valueOf(expirationDate.getTextInput().trim());

            stmt.setDouble(1, weight);
            stmt.setDate(2, date);
            stmt.setInt(3, productId);

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to update grocery info.", "Update Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
