package finalAssigments.StoreStorageApp.GUI.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MyButton extends JButton {
    private String text;
    private String fontColor;
    private String bgColor;

    public MyButton(String text, String fontColor, String bgColor) {
        this.text = text;
        this.fontColor = fontColor;
        this.bgColor = bgColor;

        this.setText(text);
        this.setBorderPainted(false);
        this.setOpaque(false);
        this.setFocusPainted(false);
        this.setFocusable(false);
        if (fontColor.length() > 0) {
            this.setForeground(Color.decode(fontColor));
            this.getModel().addChangeListener(e -> {
                ButtonModel model = this.getModel();
                if (model.isPressed()) {
                    this.setForeground(Color.LIGHT_GRAY);
                } else {
                    this.setForeground(Color.decode(fontColor));
                }
            });
        }
        if (bgColor.length() > 0) {
            this.setBackground(Color.decode(bgColor));
            this.setOpaque(true);

            this.getModel().addChangeListener(e -> {
                ButtonModel model = this.getModel();
                if (model.isPressed()) {
                    this.setBackground(Color.LIGHT_GRAY);
                } else {
                    this.setBackground(Color.decode(bgColor));
                }
            });
        }


    }

    public String getText() {
        return text;
    }
    public void setActionlistener(ActionListener listener) {
        this.addActionListener(listener);
    }
}
