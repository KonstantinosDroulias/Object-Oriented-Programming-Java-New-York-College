package weeksTasks.week05;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import static weeksTasks.week05.LaunchPage.username;

public class GamePage extends JFrame implements ActionListener {
    JLabel usernameLabel;
    JLabel scoreLabel;
    JLabel noticeLabel;
    JButton nextButton;
    HashMap<String, String> letterToImage;
    ArrayList<String> keyList;
    ImageIcon icon;
    JButton checkButton;
    JTextField firstLetterInput;
    int i = 0;
    String currentKey;
    String currentValue;
    boolean isAnswerCorrect = false;
    JLabel petImage;

    public GamePage() {
        letterToImage = new HashMap<>();
        letterToImage.put("b", "bear.jpg");
        letterToImage.put("c", "cat.jpg");
        letterToImage.put("d", "dog.jpg");
        letterToImage.put("h", "horse.jpeg");
        letterToImage.put("l", "lion.jpeg");
        letterToImage.put("p", "panda.jpg");
        letterToImage.put("r", "rabbit.jpg");
        letterToImage.put("t", "turtle.png");
        letterToImage.put("w", "wolf.jpg");
        letterToImage.put("z", "zebra.jpeg");

        keyList = new ArrayList<>(letterToImage.keySet());

        this.setTitle("What's The Animal - Letter Training");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLayout(new BorderLayout(20, 20));

        JPanel MenuBar = new JPanel();
        MenuBar.setLayout(new BorderLayout());
        usernameLabel = new JLabel(username);
        JPanel centerMenuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        centerMenuPanel.add(usernameLabel);
        centerMenuPanel.setBackground(Color.WHITE);
        MenuBar.add(centerMenuPanel, BorderLayout.CENTER);
        scoreLabel = new JLabel("0/10");
        MenuBar.add(scoreLabel, BorderLayout.EAST);
        MenuBar.setBackground(Color.WHITE);

        JPanel appContent = new JPanel();
        appContent.setLayout(new BoxLayout(appContent, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("What's The Animal's First Letter Name");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        icon = new ImageIcon("src/weeksTasks/week05/images/panda.jpg");
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(220, 150, Image.SCALE_SMOOTH);
        ImageIcon petImageIcon = new ImageIcon(scaledImage);
        petImage = new JLabel(petImageIcon);
        petImage.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel InputPanel = new JPanel();
        InputPanel.setLayout(new BoxLayout(InputPanel, BoxLayout.X_AXIS));
        firstLetterInput = new JTextField();
        checkButton = new JButton("Check");
        checkButton.addActionListener(this);
        InputPanel.add(firstLetterInput);
        InputPanel.add(checkButton);
        InputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        InputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        noticeLabel = new JLabel("Correct +1 point 🎉");
        noticeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        nextButton = new JButton("Next");
        nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextButton.addActionListener(this);

        appContent.add(title);
        appContent.add(Box.createRigidArea(new Dimension(0, 10)));
        appContent.add(petImage);
        appContent.add(Box.createRigidArea(new Dimension(0, 10)));
        appContent.add(InputPanel);
        appContent.add(Box.createRigidArea(new Dimension(0, 10)));
        appContent.add(noticeLabel);
        appContent.add(Box.createRigidArea(new Dimension(0, 30)));
        appContent.add(nextButton);

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.add(appContent);

        this.add(MenuBar, BorderLayout.NORTH);
        this.add(wrapper, BorderLayout.CENTER);
        this.setResizable(false);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nextButton) {
            if(i != 9) {
                if (isAnswerCorrect) {
                    i++;
                    String key = keyList.get(i);
                    String imageFile = letterToImage.get(key);
                    icon = new ImageIcon("src/weeksTasks/week05/images/" + imageFile);
                    Image image = icon.getImage();
                    Image scaledImage = image.getScaledInstance(220, 150, Image.SCALE_SMOOTH);
                    ImageIcon petImageIcon = new ImageIcon(scaledImage);
                    petImage.setIcon(petImageIcon);
                    scoreLabel.setText(i + 1 + "/10");
                    firstLetterInput.setText("");
                    isAnswerCorrect = false;
                    firstLetterInput.setBorder(BorderFactory.createLineBorder(Color.BLACK ));
                } else {
                    JOptionPane.showMessageDialog(GamePage.this, "You must find the answer first");
                }

            } else {
                JOptionPane.showMessageDialog(this, "You have WON🥇");
            }
        }
        if(e.getSource() == checkButton) {
            String input = firstLetterInput.getText().trim().toLowerCase();
            if(input.equals(keyList.get(i))) {
                isAnswerCorrect = true;
                firstLetterInput.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            } else {
                isAnswerCorrect = false;
                firstLetterInput.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        }

    }
}