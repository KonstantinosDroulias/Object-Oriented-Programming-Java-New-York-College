package finalAssigments.StoreStorageApp.GUI.Components;

import weeksTasks.week04.GUI.appFunctions.infoField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LabelTextInput extends JPanel {
    private String LabelTitle;
    public String labelInfo;
    public boolean search;
    private JButton searchButton;
    private String iconFileName = "search.jpg";
    JTextField textField;

    public LabelTextInput(String labelTitle, String labelInfo, boolean search) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(LEFT_ALIGNMENT);

        JPanel labelPanel = new JPanel(new BorderLayout());
        JLabel textFieldLabel = new JLabel(labelTitle);
        ImageIcon infoIcon = new ImageIcon("src/weeksTasks/week04/images/info.png");
        Image scaledInfoIcon = infoIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon infoIconScaled = new ImageIcon(scaledInfoIcon);
        JButton infoButton = new JButton(infoIconScaled);
        infoButton.addActionListener(new infoField(labelInfo));
        infoButton.setPreferredSize(new Dimension(16, 16));
        infoButton.setMaximumSize(new Dimension(16, 16));
        infoButton.setMinimumSize(new Dimension(16, 16));
        infoButton.setBorderPainted(false);
        infoButton.setContentAreaFilled(false);
        infoButton.setFocusPainted(false);
        infoButton.setOpaque(false);
        infoButton.setMargin(new Insets(0, 0, 0, 0));
        labelPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
        if (labelInfo.length() > 0) {
            labelPanel.add(textFieldLabel, BorderLayout.WEST);
            labelPanel.add(infoButton, BorderLayout.EAST);
        } else {
            labelPanel.add(textFieldLabel, BorderLayout.CENTER);
        }

        JPanel textFieldPanel = new JPanel(new BorderLayout());
        textField = new JTextField();
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        ImageIcon searchIcon = new ImageIcon("src/finalAssigments/StoreStorageApp/images/store/" + iconFileName);
        Image scaledSearchIcon = searchIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon searchImage = new ImageIcon(scaledSearchIcon);
        searchButton = new JButton(searchImage);
        searchButton.setPreferredSize(new Dimension(16, 16));
        searchButton.setMaximumSize(new Dimension(16, 16));
        searchButton.setMinimumSize(new Dimension(16, 16));
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.setFocusPainted(false);
        searchButton.setOpaque(false);
        searchButton.setMargin(new Insets(0, 0, 0, 0));
        textFieldPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        textFieldPanel.add(textField, BorderLayout.CENTER);
        if (search) {
            textFieldPanel.add(searchButton, BorderLayout.EAST);
        }
        this.add(labelPanel);
        this.add(textFieldPanel);
    }

    public String getTextInput() {
        return textField.getText();
    }

    public void setTextInput(String text) {
        textField.setText(text);
    }

    public void setActionListener(ActionListener actionListener) {
        searchButton.addActionListener(actionListener);
    }

    public void setImageIcon(String iconFileName) {
        this.iconFileName = iconFileName;

        ImageIcon searchIcon = new ImageIcon("src/finalAssigments/StoreStorageApp/images/store/" + iconFileName);
        Image scaledSearchIcon = searchIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon searchImage = new ImageIcon(scaledSearchIcon);

        searchButton.setIcon(searchImage);
    }
}
