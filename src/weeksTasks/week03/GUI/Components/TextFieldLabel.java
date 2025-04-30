package weeksTasks.week03.GUI.Components;

import javax.swing.*;
import java.awt.*;

public class TextFieldLabel extends JPanel {
    private String LabelTitle;
    JTextField textField;

    public TextFieldLabel(String labelTitle) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(LEFT_ALIGNMENT);

        JLabel textFieldLabel = new JLabel(labelTitle);
        textFieldLabel.setAlignmentX(LEFT_ALIGNMENT);

        textField = new JTextField();
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        textField.setAlignmentX(LEFT_ALIGNMENT);

        this.add(textFieldLabel);
        this.add(textField);
    }

    public String getTextInput() {
        return textField.getText();
    }
}
