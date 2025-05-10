package finalAssigments.StoreStorageApp.GUI.Pages.AllProducts.CategoriesInputs;

import finalAssigments.StoreStorageApp.GUI.Components.LabelTextInput;
import finalAssigments.StoreStorageApp.SQLQueries.FindIDUtil;
import finalAssigments.StoreStorageApp.SQLQueries.JoinTableUtil;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;


public class GroceryPanel extends JPanel implements ProductCategory {
    LabelTextInput weightInput;
    LabelTextInput expirationDate;

    public GroceryPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        weightInput = new LabelTextInput("Enter Stock in Weight", "", false);
        expirationDate = new LabelTextInput("Enter Expiration Date", "Input format must be yyyy-mm-dd", false);
        this.add(weightInput);
        this.add((Box.createVerticalStrut(10)));
        this.add(expirationDate);
        this.add(Box.createVerticalGlue());
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
}
