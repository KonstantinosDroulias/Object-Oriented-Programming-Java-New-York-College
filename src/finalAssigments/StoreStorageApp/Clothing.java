package finalAssigments.StoreStorageApp;

public class Clothing extends Categories {
    private String Size;
    private String Material;
    private String Color;
    private String hex;

    public Clothing(int id, String productName, double price, String SKU, String Size, String Material, String Color, String hex) {
        super(id, productName, price, SKU);
        this.Size = Size;
        this.Material = Material;
        this.Color = Color;
        this.hex = hex;
    }

    public String getSize() {
        return Size;
    }
    public void setSize(String size) {
        Size = size;
    }
    public String getMaterial() {
        return Material;
    }
    public void setMaterial(String material) {
        Material = material;
    }
    public String getColor() {
        return Color;
    }
    public void setColor(String color) {
        Color = color;
    }
    public String getHex() {
        return hex;
    }
    public void setHex(String hex) {
        this.hex = hex;
    }
}
