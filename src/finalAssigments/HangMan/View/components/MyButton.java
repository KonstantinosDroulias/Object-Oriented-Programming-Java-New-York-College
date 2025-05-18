package finalAssigments.HangMan.View.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MyButton extends JButton {

    public MyButton(String text) {
        super(text);

        this.setBorder(new EmptyBorder(8, 14, 8, 14));
        this.setBackground(Color.WHITE);
        this.setFocusable(Boolean.TRUE);
        this.setFocusPainted(Boolean.TRUE);
        this.setOpaque(true);
    }
}
