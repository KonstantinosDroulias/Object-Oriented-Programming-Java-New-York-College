package finalAssigments.HangMan.View;

import javax.swing.*;
import java.awt.*;

public class UsedLettersView extends JPanel {

    public UsedLettersView() {
        this.setLayout(new FlowLayout());
        this.setOpaque(false);

        // TODO: take from array of actuall used letters
        JLabel lblUsedLetters = new JLabel("A, B, C, D");
        lblUsedLetters.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblUsedLetters);
    }
}
