package finalAssigments.StoreStorageApp.GUI.Pages.AllProducts.CategoriesInputs;

import finalAssigments.StoreStorageApp.GUI.Components.LabelTextInput;
import finalAssigments.StoreStorageApp.GUI.Components.MyComboBox;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ElectronicsPanel extends JPanel {

    public ElectronicsPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        ArrayList<String> testData = new ArrayList<>();
        testData.add("Apple");
        testData.add("Samsung");
        MyComboBox brandsSelection = new MyComboBox("Product Brand", testData);
        LabelTextInput warrantyPeriodInput = new LabelTextInput("Warranty period", "Give the Number of Years", false);
        LabelTextInput stockInput = new LabelTextInput("Stock", "", false);

        brandsSelection.setAlignmentX(Component.LEFT_ALIGNMENT);
        warrantyPeriodInput.setAlignmentX(Component.LEFT_ALIGNMENT);
        stockInput.setAlignmentX(Component.LEFT_ALIGNMENT);


        this.add(brandsSelection);
        this.add((Box.createVerticalStrut(10)));
        this.add(warrantyPeriodInput);
        this.add((Box.createVerticalStrut(10)));
        this.add(stockInput);
        this.add(Box.createVerticalGlue());
    }
}
