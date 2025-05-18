package finalAssigments.HangMan.View;

import finalAssigments.HangMan.Model.UserModel;
import finalAssigments.HangMan.View.components.MyButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static finalAssigments.HangMan.HangMan.jaroFont;

public class StatsView extends JPanel {
    private UserModel user;

    public StatsView(UserModel user) {
        this.user = user;

        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(12, 8, 12, 8));

        //JPanel livesLeft = new JPanel();
        //JLabel livesLeftLabel = new JLabel("Lives: " + Lives);


        JPanel userStats = new JPanel();
        userStats.setLayout(new BoxLayout(userStats, BoxLayout.Y_AXIS));
        userStats.setOpaque(false);
        try {
            JLabel userName = new JLabel(user.getUsername());
            userName.setAlignmentX(Component.RIGHT_ALIGNMENT);
            JLabel scoreLabel = new JLabel("Score: " + user.getScore());
            scoreLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            userStats.add(userName);
            userStats.add(scoreLabel);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        this.setBackground(Color.WHITE);
        this.setOpaque(true);

        this.add(userStats, BorderLayout.EAST);
    }
}
