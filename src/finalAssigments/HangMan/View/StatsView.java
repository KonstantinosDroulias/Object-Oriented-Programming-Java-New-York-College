package finalAssigments.HangMan.View;

import finalAssigments.HangMan.Model.GameModel;
import finalAssigments.HangMan.Model.UserModel;
import finalAssigments.HangMan.View.components.MyButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static finalAssigments.HangMan.HangMan.jaroFont;

public class StatsView extends JPanel {
    private UserModel user;
    private GameModel game;
    private JLabel scoreLabel;

    public StatsView(UserModel user, GameModel Game) {
        this.user = user;
        this.game = Game;

        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(12, 8, 12, 8));


        JPanel gameStats = new JPanel();
        gameStats.setLayout(new BoxLayout(gameStats, BoxLayout.X_AXIS));
        gameStats.setOpaque(false);

        ImageIcon rawIcon = new ImageIcon("src/finalAssigments/HangMan/View/images/pixelarticons_heart.png");
        Image scaledIcon = rawIcon.getImage().getScaledInstance(14, 14, Image.SCALE_SMOOTH);
        ImageIcon heartIcon = new ImageIcon(scaledIcon);
        JLabel heartIconLabel = new JLabel(heartIcon);

        gameStats.add(heartIconLabel);
        gameStats.add(game.getLivesLabel());



        JPanel userStats = new JPanel();
        userStats.setLayout(new BoxLayout(userStats, BoxLayout.Y_AXIS));
        userStats.setOpaque(false);
        try {
            JLabel userName = new JLabel(user.getUsername());
            userName.setAlignmentX(Component.RIGHT_ALIGNMENT);
            scoreLabel = new JLabel("Score: " + user.getScore());
            scoreLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            userStats.add(userName);
            userStats.add(scoreLabel);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        this.setBackground(Color.WHITE);
        this.setOpaque(true);

        this.add(gameStats, BorderLayout.WEST);
        this.add(userStats, BorderLayout.EAST);
    }

    public void updateScoreDisplay(int newScore) {
        scoreLabel.setText("Score: " + newScore);
    }
}
