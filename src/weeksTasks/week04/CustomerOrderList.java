package weeksTasks.week04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerOrderList extends ArrayList<CustomerOrder> {
    public void loadFromDatabase() {
        DBConnection db = new DBConnection();
        try {
            Connection con = db.getConnection();
            String sql = """
                SELECT o.OrderID, o.OrderDate, o.Amount, o.CustomerID,
                       c.CustomerName, c.CustomerEmail
                FROM orders o
                JOIN customer c ON o.CustomerID = c.CustomerID
            """;

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("CustomerName"),
                        rs.getString("CustomerEmail")
                );

                Order order = new Order(
                        rs.getInt("OrderID"),
                        rs.getDate("OrderDate"),
                        rs.getDouble("Amount"),
                        rs.getInt("CustomerID")
                );

                this.add(new CustomerOrder(customer, order));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
