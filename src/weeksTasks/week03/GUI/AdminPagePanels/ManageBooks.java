package weeksTasks.week03.GUI.AdminPagePanels;

import javax.swing.*;
import java.awt.*;

public class ManageBooks extends JPanel {

    public ManageBooks() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(16, 20, 16, 20));

        JLabel titleLabel = new JLabel("All Books:");
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(titleLabel);

        this.add(Box.createRigidArea(new Dimension(0, 10)));

        JList<String> bookList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(bookList);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane.setPreferredSize(new Dimension(300, 400));
        this.add(scrollPane);

        this.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel removeButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton removeBookButton = new JButton("Remove");
        removeButtonPanel.add(removeBookButton);
        removeButtonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(removeButtonPanel);
    }
}
