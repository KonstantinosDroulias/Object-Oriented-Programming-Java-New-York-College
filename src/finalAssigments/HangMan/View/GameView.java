package finalAssigments.HangMan.View;

import finalAssigments.HangMan.Model.GameModel;
import finalAssigments.HangMan.Model.UserModel;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    private UserModel user;
    private GameModel game;
    private BottomView bottomPanel;
    private JLabel secretWord;
    private HangManDraw drawing;
    private StatsView statsView;

    public GameView(UserModel user, GameModel Game) {
        this.user = user;
        this.game = Game;

        this.setLayout(new BorderLayout(8,8));


        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        secretWord = new JLabel();
        secretWord.setText(game.getRevealedWord());
        secretWord.setAlignmentX(Component.CENTER_ALIGNMENT);

        drawing = new HangManDraw();

        bottomPanel = new BottomView(game);

        centerPanel.add(secretWord);
        centerPanel.add(drawing);

        statsView = new StatsView(user, game);
        this.add(statsView, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);


    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public GameView getGameView() {
        return this;
    }

    public BottomView getBottomPanel() {
        return bottomPanel;
    }

    public void setSecretWord(String secretWord) {
        this.secretWord.setText(secretWord);
    }

    public void hangPart() {
        drawing.decreaseLivesLeft();
    }

    public UsedLettersView getUsedLettersView() {
        return bottomPanel.getUsedLettersView();
    }

    public void updateScoreView(int score) {
        statsView.updateScoreDisplay(score);
    }

    public void resetDrawing() {
        drawing.setLivesLeft(6);
    }

    /* - END SCREEN - */
    public void showEndScreen(String message, Runnable onPlayAgain) {
        JPanel overlay = new JPanel();
        overlay.setLayout(new BoxLayout(overlay, BoxLayout.Y_AXIS));
        overlay.setOpaque(true);
        overlay.setBackground(new Color(0, 0, 0, 180)); // semi-transparent black

        JLabel label = new JLabel(message);
        label.setFont(new Font("Arial", Font.BOLD, 36));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        resetDrawing();

        JButton playAgain = new JButton("Play Again");
        playAgain.setAlignmentX(Component.CENTER_ALIGNMENT);
        playAgain.addActionListener(e -> {
            this.remove(overlay);
            this.revalidate();
            this.repaint();
            onPlayAgain.run(); // this will reset the game
        });

        overlay.add(Box.createVerticalGlue());
        overlay.add(label);
        overlay.add(Box.createRigidArea(new Dimension(0, 20)));
        overlay.add(playAgain);
        overlay.add(Box.createVerticalGlue());

        overlay.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.setLayout(null);
        this.add(overlay, JLayeredPane.POPUP_LAYER);
        this.revalidate();
        this.repaint();
    }

}
