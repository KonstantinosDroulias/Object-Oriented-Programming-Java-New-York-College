package weeksTasks.week04;


import java.sql.Date;

public class Order {
    private int orderID;
    private Date date;
    private double amount;
    private int customerID;

    public Order(int orderID, Date date, double amount, int customerID) {
        this.orderID = orderID;
        this.date = date;
        this.amount = amount;
        this.customerID = customerID;
    }

    public int getOrderID() {
        return orderID;
    }
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }
    public int getCustomerID() {
        return customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
