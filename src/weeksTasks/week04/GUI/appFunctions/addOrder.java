package weeksTasks.week04.GUI.appFunctions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import weeksTasks.week04.DBConnection;
import weeksTasks.week04.GUI.leftcolumn;

import javax.swing.*;


public class addOrder implements ActionListener {
    private leftcolumn panel;

    public addOrder(leftcolumn panel) {
        this.panel = panel;
    }
    //Above code I learned from chatgpt it says it called Dependency Injection - Interesting :) "I was trying with public static to bring the input field values"
    @Override
    public void actionPerformed(ActionEvent e) {
        DBConnection db = new DBConnection();
        try {
            Connection con = db.getConnection();
            int ID = searchCustomer();
            if (ID == -1) {
                String addCustomer = "INSERT INTO customer (CustomerName, CustomerEmail) VALUES (?, ?)";
                PreparedStatement ps = con.prepareStatement(addCustomer);
                String name = panel.getNameOrderLabel().getTextInput();
                String email = panel.getEmailOrder().getTextInput();
                ps.setString(1, name);
                ps.setString(2, email);
                ps.executeUpdate();
                String getID = "SELECT CustomerID FROM customer WHERE CustomerEmail = ?";
                PreparedStatement pst = con.prepareStatement(getID);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    ID = rs.getInt("CustomerID");
                } else {
                    JOptionPane.showMessageDialog(null, "Customer not added");
                }
            }

            String addOrder = "INSERT INTO orders (Amount, CustomerID) VALUES (?, ?)";
            PreparedStatement stmt = con.prepareStatement(addOrder);
            stmt.setInt(2, ID);
            try {
                String priceText = panel.getPriceOrderLabel().getTextInput();
                double price = Double.parseDouble(priceText.trim());
                stmt.setDouble(1, price);
                stmt.executeUpdate(); // ✅ run the query only if price is valid
                JOptionPane.showMessageDialog(null, "Order added successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid price (e.g. 9.99)");
            }
        } catch (SQLException d) {
            d.printStackTrace();
        }
    }

    public int searchCustomer() {
        DBConnection db = new DBConnection();
        try {
            Connection con = db.getConnection();
            String searchEmailQuery = "SELECT CustomerID FROM customer WHERE CustomerEmail = ?";
            PreparedStatement stmt = con.prepareStatement(searchEmailQuery);

            String email = panel.getEmailOrder().getTextInput();
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();  // ✅ assign the result set

            if (rs.next()) {
                int customerId = rs.getInt("CustomerID");
                return customerId;  // ✅ return ID if found
            } else {
                return -1;
            }
    } catch (SQLException d) {
            d.printStackTrace();
            return -1;
        }
    }

}
