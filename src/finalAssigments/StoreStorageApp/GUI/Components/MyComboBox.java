package finalAssigments.StoreStorageApp.GUI.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyComboBox extends JPanel {
    JComboBox<String> comboBox;

    public MyComboBox(String optionLabel, ArrayList<String> options) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        JLabel comboBoxLabel = new JLabel("Choose " + optionLabel);
        comboBox = new JComboBox<>(options.toArray(new String[0]));

        // Use another panel for vertical layout of label + combo
        JPanel inner = new JPanel();
        inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));
        inner.setOpaque(false);
        comboBox.setAlignmentX(LEFT_ALIGNMENT);
        comboBoxLabel.setAlignmentX(LEFT_ALIGNMENT);

        inner.add(comboBoxLabel);
        inner.add(comboBox);

        wrapper.setMaximumSize(inner.getPreferredSize());
        wrapper.add(inner);
        this.add(wrapper);
    }

    public void setActionListener(ActionListener listener) {
        comboBox.addActionListener(listener);
    }

    public String getSelectedItem() {
        return (String) comboBox.getSelectedItem();
    }

    public void setSelectedItem(String value) {
        comboBox.setSelectedItem(value);
    }
}

