package finalAssigments.StoreStorageApp;

public class Electronics extends Products {
    private String BrandName;
    private int Stock;
    private int Warranty;


    public Electronics(int id, String productName, double price, String SKU, String imageFileName, String BrandName, int Stock, int Warranty) {
        super(id, productName, price, SKU, imageFileName);
        this.BrandName = BrandName;
        this.Stock = Stock;
        this.Warranty = Warranty;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public int getWarranty() {
        return Warranty;
    }

    public void setWarranty(int Warranty) {
        this.Warranty = Warranty;
    }
}

