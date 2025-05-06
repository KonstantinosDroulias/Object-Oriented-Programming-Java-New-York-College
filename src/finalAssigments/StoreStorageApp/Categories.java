package finalAssigments.StoreStorageApp;

abstract class Categories{
    private int id;
    private String productName;
    private double price;
    private String SKU;

    public Categories(int id, String productName, double price, String SKU) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.SKU = SKU;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getSKU() {
        return SKU;
    }
    public void setSKU(String SKU) {
        this.SKU = SKU;
    }
}
