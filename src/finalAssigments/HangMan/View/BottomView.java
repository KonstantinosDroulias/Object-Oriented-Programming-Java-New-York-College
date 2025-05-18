package finalAssigments.HangMan.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.View;
import java.awt.*;

public class BottomView extends JPanel {
    private KeyboardPanel keyboardPanel = new KeyboardPanel();

    public BottomView() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(12, 8, 12, 8));
        this.setBackground(Color.WHITE);
        this.setOpaque(true);

        this.add(new LeaderBoardView(), BorderLayout.WEST);
        this.add(keyboardPanel, BorderLayout.CENTER);
        this.add(new UsedLettersView(), BorderLayout.EAST);

    }

    public KeyboardPanel getKeyboardPanel() {
        return keyboardPanel;
    }

}

