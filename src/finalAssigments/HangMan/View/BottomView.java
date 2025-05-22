package finalAssigments.HangMan.View;

import finalAssigments.HangMan.Model.GameModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.View;
import java.awt.*;

public class BottomView extends JPanel {
    private KeyboardPanel keyboardPanel;
    private GameModel game;
    private UsedLettersView usedLettersView;

    public BottomView(GameModel gameModel) {
        this.game = gameModel;
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(12, 8, 12, 8));
        this.setBackground(Color.WHITE);
        this.setOpaque(true);

        keyboardPanel = new KeyboardPanel();
        usedLettersView = new UsedLettersView(game);

        this.add(new LeaderBoardView(), BorderLayout.WEST);
        this.add(keyboardPanel, BorderLayout.CENTER);
        this.add(usedLettersView, BorderLayout.EAST);

    }

    public KeyboardPanel getKeyboardPanel() {
        return keyboardPanel;
    }
    public UsedLettersView getUsedLettersView() {
        return usedLettersView;
    }

}

