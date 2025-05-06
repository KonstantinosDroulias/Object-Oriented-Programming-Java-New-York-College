package finalAssigments.StoreStorageApp;

public class Electronics extends Categories {
    private String BrandName;
    private int Warranty;

    public Electronics(int id, String productName, double price, String SKU, String BrandName, int Warranty) {
        super(id, productName, price, SKU);
        this.BrandName = BrandName;
        this.Warranty = Warranty;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    public int getWarranty() {
        return Warranty;
    }

    public void setWarranty(int Warranty) {
        this.Warranty = Warranty;
    }
}

