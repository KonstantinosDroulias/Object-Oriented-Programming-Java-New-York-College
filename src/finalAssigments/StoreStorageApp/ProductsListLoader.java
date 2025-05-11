package finalAssigments.StoreStorageApp;

import java.sql.*;
import java.util.ArrayList;

public class ProductsListLoader {

    public static ArrayList<Products> loadAllProducts(Connection conn) {
        ArrayList<Products> products = new ArrayList<>();

        String productQuery = "SELECT ProductID, Name, SKU, Price, Image FROM Products";
        String electronicsQuery = "SELECT BrandID, Stock, WarrantyPeriod FROM Electronics WHERE ProductID = ?";
        String brandQuery = "SELECT BrandName FROM Brands WHERE BrandID = ?";
        String groceryQuery = "SELECT WeightStock, ExpirationDate FROM Groceries WHERE ProductID = ?";
        String clothingQuery = "SELECT ClothingID, MaterialID FROM Clothing WHERE ProductID = ?";
        String materialQuery = "SELECT Material FROM Material WHERE MaterialID = ?";
        String sizeIdsQuery = "SELECT SizeID FROM ClothingSizes WHERE ClothingID = ?";
        String sizeQuery = "SELECT Size FROM Size WHERE SizeID = ?";
        String colorIdsQuery = "SELECT ColorID FROM ClothingColors WHERE ClothingID = ?";
        String colorQuery = "SELECT Color, HexValue FROM Colors WHERE ColorID = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(productQuery);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("ProductID");
                String name = rs.getString("Name");
                String sku = rs.getString("SKU");
                double price = rs.getDouble("Price");
                String image = rs.getString("Image");

                // ELECTRONICS
                try {
                    PreparedStatement eStmt = conn.prepareStatement(electronicsQuery);
                    eStmt.setInt(1, productId);
                    ResultSet ers = eStmt.executeQuery();
                    if (ers.next()) {
                        int brandId = ers.getInt("BrandID");
                        int stock = ers.getInt("Stock");
                        int warranty = ers.getInt("WarrantyPeriod");

                        PreparedStatement brandStmt = conn.prepareStatement(brandQuery);
                        brandStmt.setInt(1, brandId);
                        ResultSet rsBrand = brandStmt.executeQuery();
                        if (rsBrand.next()) {
                            String brandName = rsBrand.getString("BrandName");
                            products.add(new Electronics(productId, name, price, sku, image, brandName, stock, warranty));
                        }
                        continue;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // GROCERIES
                try {
                    PreparedStatement gStmt = conn.prepareStatement(groceryQuery);
                    gStmt.setInt(1, productId);
                    ResultSet grs = gStmt.executeQuery();
                    if (grs.next()) {
                        double weight = grs.getDouble("WeightStock");
                        Date expiration = grs.getDate("ExpirationDate");
                        products.add(new Grocery(productId, name, price, sku, image, weight, expiration));
                        continue;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // CLOTHING
                try {
                    PreparedStatement cStmt = conn.prepareStatement(clothingQuery);
                    cStmt.setInt(1, productId);
                    ResultSet crs = cStmt.executeQuery();
                    if (crs.next()) {
                        int clothingId = crs.getInt("ClothingID");
                        int materialId = crs.getInt("MaterialID");

                        String material = "";
                        try {
                            PreparedStatement mStmt = conn.prepareStatement(materialQuery);
                            mStmt.setInt(1, materialId);
                            ResultSet mrs = mStmt.executeQuery();
                            if (mrs.next()) material = mrs.getString("Material");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        ArrayList<String> sizes = new ArrayList<>();
                        try {
                            PreparedStatement sIdStmt = conn.prepareStatement(sizeIdsQuery);
                            sIdStmt.setInt(1, clothingId);
                            ResultSet sIdRs = sIdStmt.executeQuery();
                            while (sIdRs.next()) {
                                int sizeId = sIdRs.getInt("SizeID");
                                PreparedStatement sStmt = conn.prepareStatement(sizeQuery);
                                sStmt.setInt(1, sizeId);
                                ResultSet srs = sStmt.executeQuery();
                                if (srs.next()) sizes.add(srs.getString("Size"));
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        ArrayList<String> colors = new ArrayList<>();
                        ArrayList<String> hexes = new ArrayList<>();
                        try {
                            PreparedStatement colIdStmt = conn.prepareStatement(colorIdsQuery);
                            colIdStmt.setInt(1, clothingId);
                            ResultSet colIdRs = colIdStmt.executeQuery();
                            while (colIdRs.next()) {
                                int colorId = colIdRs.getInt("ColorID");
                                PreparedStatement colStmt = conn.prepareStatement(colorQuery);
                                colStmt.setInt(1, colorId);
                                ResultSet colrs = colStmt.executeQuery();
                                if (colrs.next()) {
                                    colors.add(colrs.getString("Color"));
                                    hexes.add(colrs.getString("HexValue"));
                                }
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        products.add(new Clothing(productId, name, price, sku, image, sizes, material, colors, hexes));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
}
