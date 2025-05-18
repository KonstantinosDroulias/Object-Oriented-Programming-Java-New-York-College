package finalAssigments.HangMan.View;

import finalAssigments.HangMan.HangMan;
import finalAssigments.HangMan.Model.GameModel;
import finalAssigments.HangMan.Model.UserModel;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;

public class GameView extends JPanel {
    private UserModel user;
    private BottomView bottomPanel = new BottomView();

    public GameView(UserModel user) {
        this.user = user;

        this.setLayout(new BorderLayout(8,8));


        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel secretWord = new JLabel(new GameModel().getSecretWord());
        secretWord.setAlignmentX(Component.CENTER_ALIGNMENT);

        HangManDraw drawing = new HangManDraw();

        centerPanel.add(secretWord);
        centerPanel.add(drawing);

        this.add(new StatsView(user), BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);


    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public int getLives() {
        return 0;
    }

    public GameView getGameView() {
        return this;
    }

    public BottomView getBottomPanel() {
        return bottomPanel;
    }
}
