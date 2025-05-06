package finalAssigments.StoreStorageApp.GUI.Pages.AllProducts.CategoriesInputs;

import finalAssigments.StoreStorageApp.GUI.Components.LabelTextInput;

import javax.swing.*;
import java.awt.*;


public class GroceryPanel extends JPanel {

    public GroceryPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        LabelTextInput weightInput = new LabelTextInput("Enter Stock in Weight", "", false);
        LabelTextInput expirationDate = new LabelTextInput("Enter Expiration Date", "Input format must be yyyy-mm-dd", false);
        this.add(weightInput);
        this.add((Box.createVerticalStrut(10)));
        this.add(expirationDate);
        this.add(Box.createVerticalGlue());
    }
}
