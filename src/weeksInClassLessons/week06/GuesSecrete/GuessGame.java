package weeksInClassLessons.week06.GuesSecrete;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GuessGame {

    public static void main(String[] args) {
        Random rand = new Random();

        int secrete = rand.nextInt(1, 100);

        JFrame window = new JFrame();

        JPanel northPanel = new JPanel();
        JLabel heading = new JLabel("Guess The Secrete");
        JTextField wordField = new JTextField(15);

        northPanel.add(heading);
        northPanel.add(wordField);

        JPanel centerPanel = new JPanel();
        JButton guessBtn  = new JButton("Guess");

        centerPanel.add(guessBtn);

        JPanel southPanel = new JPanel();
        JLabel resultLabel = new JLabel("Your Guess is: ");

        southPanel.add(resultLabel);

        window.setLayout(new BorderLayout());
        window.add(northPanel, BorderLayout.NORTH);
        window.add(centerPanel, BorderLayout.CENTER);
        window.add(southPanel, BorderLayout.SOUTH);

        window.setSize(600, 400);
        window.setTitle("Guess Game");
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
