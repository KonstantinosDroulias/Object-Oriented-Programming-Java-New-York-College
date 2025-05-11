package finalAssigments.StoreStorageApp;

import java.util.ArrayList;

public class Clothing extends Products {
    private ArrayList<String> Size;
    private String Material;
    private ArrayList<String> Color;
    private ArrayList<String> hex;

    public Clothing(int id, String productName, double price, String SKU, String imageFileName, ArrayList<String> Size, String Material, ArrayList<String> Color, ArrayList<String> hex) {
        super(id, productName, price, SKU, imageFileName);
        this.Size = Size;
        this.Material = Material;
        this.Color = Color;
        this.hex = hex;
    }

    public ArrayList<String> getSize() {
        return Size;
    }
    public void setSize(ArrayList<String> Size) {
        this.Size = Size;
    }
    public String getMaterial() {
        return Material;
    }
    public void setMaterial(String material) {
        Material = material;
    }
    public ArrayList<String> getColor() {
        return Color;
    }
    public void setColor(ArrayList<String> Color) {
        this.Color = Color;
    }
    public ArrayList<String> getHex() {
        return hex;
    }
    public void setHex(ArrayList<String> hex) {
        this.hex = hex;
    }
}
