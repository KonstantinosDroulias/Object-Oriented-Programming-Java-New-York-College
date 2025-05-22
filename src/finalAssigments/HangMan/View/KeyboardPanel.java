package finalAssigments.HangMan.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyboardPanel extends JPanel {
    private ArrayList<JButton> buttons = new ArrayList<>();

    public KeyboardPanel() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setOpaque(false);
       ArrayList<Character> keyboardKeys = new ArrayList<>();
        keyboardKeys.add('A');
        keyboardKeys.add('B');
        keyboardKeys.add('C');
        keyboardKeys.add('D');
        keyboardKeys.add('E');
        keyboardKeys.add('F');
        keyboardKeys.add('G');
        keyboardKeys.add('H');
        keyboardKeys.add('I');
        keyboardKeys.add('J');
        keyboardKeys.add('K');
        keyboardKeys.add('L');
        keyboardKeys.add('M');
        keyboardKeys.add('N');
        keyboardKeys.add('O');
        keyboardKeys.add('P');
        keyboardKeys.add('Q');
        keyboardKeys.add('R');
        keyboardKeys.add('S');
        keyboardKeys.add('T');
        keyboardKeys.add('U');
        keyboardKeys.add('V');
        keyboardKeys.add('W');
        keyboardKeys.add('X');
        keyboardKeys.add('Y');
        keyboardKeys.add('Z');

        for (Character keyboardKey : keyboardKeys) {
           JButton button = new JButton(keyboardKey.toString());
           button.setAlignmentX(Component.CENTER_ALIGNMENT);
           buttons.add(button);
           this.add(button);
       }
    }

    public void addKeyListener(ActionListener actionListener) {
        for (JButton button : buttons) {
            button.addActionListener(actionListener);
        }
    }

    public void disableAllKeys() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }

    public void resetAllKeys() {
        for (JButton button : buttons) {
            button.setEnabled(true);
        }
    }

    public void disableKey(char letter) {
        for (JButton button : buttons) {
            if (button.getText().equals(String.valueOf(letter))) {
                button.setEnabled(false);
                break;
            }
        }
    }


    public ArrayList<JButton> getButtons() {
        return buttons;
    }
}
