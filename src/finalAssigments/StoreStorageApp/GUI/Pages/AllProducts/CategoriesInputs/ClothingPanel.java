package finalAssigments.StoreStorageApp.GUI.Pages.AllProducts.CategoriesInputs;

import finalAssigments.StoreStorageApp.GUI.Components.MultiSelection;
import finalAssigments.StoreStorageApp.GUI.Components.MyComboBox;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ClothingPanel extends JPanel {

    public ClothingPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        ArrayList<String> clothingSizes = new ArrayList<>();
        clothingSizes.add("S");
        clothingSizes.add("M");
        clothingSizes.add("L");
        MultiSelection sizesSelection = new MultiSelection(clothingSizes);
        MyComboBox materialSelection = new MyComboBox("Product Material", clothingSizes);
        ArrayList<String> clothingColors = new ArrayList<>();
        clothingColors.add("RED");
        clothingColors.add("BLUE");
        clothingColors.add("WHITE");
        MultiSelection colorsSelection = new MultiSelection(clothingColors);

        sizesSelection.setAlignmentX(Component.LEFT_ALIGNMENT);
        materialSelection.setAlignmentX(Component.LEFT_ALIGNMENT);
        colorsSelection.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.add(sizesSelection);
        this.add((Box.createVerticalStrut(10)));
        this.add(materialSelection);
        this.add((Box.createVerticalStrut(10)));
        this.add(colorsSelection);
        this.add(Box.createVerticalGlue());
    }
}
