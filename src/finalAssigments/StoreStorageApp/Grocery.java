package finalAssigments.StoreStorageApp;

import java.sql.Date;

public class Grocery extends Categories {
    private double Weight;
    private Date expirationDate;

    public Grocery(int id, String productName, double price, String SKU, double weight, Date expirationDate) {
        super(id, productName, price, SKU);
        Weight = weight;
        this.expirationDate = expirationDate;
    }

    public double getWeight() {
        return Weight;
    }
    public void setWeight(double weight) {
        Weight = weight;
    }
    public Date getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
