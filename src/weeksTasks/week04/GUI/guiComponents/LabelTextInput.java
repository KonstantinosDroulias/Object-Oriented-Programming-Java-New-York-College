package weeksTasks.week04.GUI.guiComponents;

import weeksTasks.week04.GUI.appFunctions.infoField;

import javax.swing.*;
import java.awt.*;

public class LabelTextInput extends JPanel {
    private String LabelTitle;
    public String labelInfo;
    JTextField textField;

    public LabelTextInput(String labelTitle, String labelInfo) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(LEFT_ALIGNMENT);

        JPanel labelPanel = new JPanel(new BorderLayout());
        JLabel textFieldLabel = new JLabel(labelTitle);
        ImageIcon infoIcon = new ImageIcon("src/weeksTasks/week04/images/info.png");
        Image scaledIcon = infoIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon infoIconScaled = new ImageIcon(scaledIcon);
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
        labelPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20)); // limit height of row
        labelPanel.add(textFieldLabel, BorderLayout.WEST);
        labelPanel.add(infoButton, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        textField = new JTextField();
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        buttonPanel.add(textField, BorderLayout.CENTER);

        this.add(labelPanel);
        this.add(textField);
    }
    public String getTextInput() {
        return textField.getText();
    }
}
