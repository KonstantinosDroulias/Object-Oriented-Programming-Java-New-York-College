package finalAssigments.StoreStorageApp.GUI.Components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MultiSelection extends JPanel {
    private ArrayList<JCheckBox> checkBoxList = new ArrayList<>();

    // Constructor takes list of options to display as checkboxes
    public MultiSelection(ArrayList<String> options) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel wrapperPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        wrapperPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel inner = new JPanel();
        inner.setLayout(new BoxLayout(inner, BoxLayout.X_AXIS));
        inner.setOpaque(false);

        for (String label : options) {
            JCheckBox box = new JCheckBox(label);
            checkBoxList.add(box);
            box.setAlignmentX(Component.CENTER_ALIGNMENT);
            inner.add(box);
        }

        wrapperPanel.setMaximumSize(inner.getPreferredSize());
        wrapperPanel.add(inner);
        this.add(wrapperPanel);
    }

    // Get list of selected values (e.g., for saving to DB or updating UI)
    public ArrayList<String> getSelectedItems() {
        ArrayList<String> selected = new ArrayList<>();
        for (JCheckBox box : checkBoxList) {
            if (box.isSelected()) {
                selected.add(box.getText());
            }
        }
        return selected;
    }

    // Set selected checkboxes based on input list
    public void setSelectedItems(ArrayList<String> itemsToSelect) {
        for (JCheckBox box : checkBoxList) {
            box.setSelected(itemsToSelect.contains(box.getText()));
        }
    }
}
