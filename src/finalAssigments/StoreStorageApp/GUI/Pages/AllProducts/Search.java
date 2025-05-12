package finalAssigments.StoreStorageApp.GUI.Pages.AllProducts;

import finalAssigments.StoreStorageApp.DBConnection;
import finalAssigments.StoreStorageApp.Products;

import javax.swing.*;
import java.util.ArrayList;

public class Search {
    public Search(String searchQuery, DefaultListModel<Products> model, ArrayList<Products> fullList) {
        model.clear();
        for (Products p : fullList) {
            if (p.getProductName().toLowerCase().contains(searchQuery.toLowerCase()) ||
                    p.getSKU().toLowerCase().contains(searchQuery.toLowerCase())) {
                model.addElement(p);
            }
        }
    }
}

