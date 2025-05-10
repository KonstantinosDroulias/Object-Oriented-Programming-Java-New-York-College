package finalAssigments.StoreStorageApp.GUI.Pages.AllProducts.CategoriesInputs;

import java.sql.Connection;
import java.sql.SQLException;

public interface ProductCategory {
    void insertCategoryData(int productID, Connection conn) throws SQLException;

}
