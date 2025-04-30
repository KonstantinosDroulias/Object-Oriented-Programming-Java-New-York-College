package weeksTasks.week04;

public class Order {
    private int orderID;
    private float amount;
    private String date;
    private int customerID;

    public Order(int orderID, String date, float amount, int customerID) {
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
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public float getAmount() {
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
