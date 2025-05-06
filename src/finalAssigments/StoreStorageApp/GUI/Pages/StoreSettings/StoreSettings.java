package finalAssigments.StoreStorageApp.GUI.Pages.StoreSettings;

import finalAssigments.StoreStorageApp.GUI.Components.MyButton;
import finalAssigments.StoreStorageApp.GUI.Components.MyComboBox;

import javax.print.attribute.standard.DialogOwner;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class StoreSettings extends JPanel {

    public StoreSettings() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(LEFT_ALIGNMENT);
        Border outer = BorderFactory.createTitledBorder("Store Settings");
        Border inner = new EmptyBorder(16, 16, 16, 16);
        this.setBorder(BorderFactory.createCompoundBorder(outer, inner));


        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setAlignmentX(LEFT_ALIGNMENT);
        ArrayList<String> themes = new ArrayList<>();
        themes.add("Light");
        themes.add("Dark");
        MyComboBox chooseTheme = new MyComboBox("Choose Theme", themes);
        topPanel.add(chooseTheme);

        JPanel selectPanel = new JPanel();
        selectPanel.setLayout(new BoxLayout(selectPanel, BoxLayout.X_AXIS));
        selectPanel.setAlignmentX(LEFT_ALIGNMENT);
        ArrayList<String> currencies = new ArrayList<>();
        currencies.add("EUR");
        currencies.add("USD");
        MyComboBox chooseCurrency = new MyComboBox("Choose Currency", currencies);
        ArrayList<String> weightMetrics = new ArrayList<>()
;       weightMetrics.add("kg");
        weightMetrics.add("lbs");
        MyComboBox chooseWeight = new MyComboBox("Choose Weight Metric", weightMetrics);
        selectPanel.add(chooseCurrency);
        chooseCurrency.add(Box.createHorizontalStrut(10));
        selectPanel.add(chooseWeight);

        MyButton UploadLogo = new MyButton("Upload Logo", "0xFFFFFF", "0xA081FF");
        UploadLogo.setAlignmentX(LEFT_ALIGNMENT);
        MyButton ApplyChanges = new MyButton("Apply Changes", "0xFFFFFF", "0x003B66");

        topPanel.setMaximumSize(topPanel.getPreferredSize());
        selectPanel.setMaximumSize(selectPanel.getPreferredSize());

        this.add(topPanel);
        this.add(Box.createVerticalStrut(16));
        this.add(selectPanel);
        this.add(Box.createVerticalStrut(16));
        this.add(UploadLogo);
        this.add(Box.createVerticalStrut(10));
        this.add(ApplyChanges);
        this.add(Box.createVerticalGlue());
    }
}
