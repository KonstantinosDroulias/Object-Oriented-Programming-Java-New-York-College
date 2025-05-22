package finalAssigments.HangMan.View;

import finalAssigments.HangMan.Model.GameModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UsedLettersView extends JPanel {
    private GameModel game;
    private JLabel lblUsedLetters;

    public UsedLettersView(GameModel gameModel) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(false);
        this.game = gameModel;

        lblUsedLetters = new JLabel();
        lblUsedLetters.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblUsedLetters);

        updateUsedLetters();
    }

    public void updateUsedLetters() {
        ArrayList<Character> used = game.getLettersUsed();
        StringBuilder sb = new StringBuilder(); // I got help from ai for the StringBuilder

        for (int i = 0; i < used.size(); i++) {
            sb.append(used.get(i));
            if (i < used.size() - 1) sb.append(", ");
        }

        lblUsedLetters.setText(sb.toString());
    }
}
