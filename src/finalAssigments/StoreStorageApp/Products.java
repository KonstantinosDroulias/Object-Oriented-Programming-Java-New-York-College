package finalAssigments.StoreStorageApp;

public abstract class Products {
    private int id;
    private String productName;
    private double price;
    private String SKU;
    private String imageFileName;

    public Products(int id, String productName, double price, String SKU, String imageFileName) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.SKU = SKU;
        this.imageFileName = imageFileName;
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
    public String getImageFileName() {
        return imageFileName;
    }
    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
}
