package weeksTasks.week05;

import weeksTasks.week03.GUI.HomePage;
import weeksTasks.week03.GUI.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchPage extends JFrame implements ActionListener {
    JButton startButton;
    GamePage gamePage;
    JTextField usernameField;
    JLabel titleLabel;
    public static String username;

    public LaunchPage() {
        this.setTitle("What's The Animal - Letter Training");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLayout(new BorderLayout(20, 20));

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        titleLabel = new JLabel("Give a Username");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameField = new JTextField();
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        startButton = new JButton("Start");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(this);

        JPanel wrapper = new JPanel(new GridBagLayout());

        menuPanel.add(titleLabel);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(usernameField);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(startButton);

        wrapper.add(menuPanel);
        this.add(wrapper, BorderLayout.CENTER);

        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton) {
            if (gamePage == null || !gamePage.isDisplayable()) {
                if (!usernameField.getText().isEmpty()) {
                    username = usernameField.getText();
                    gamePage = new GamePage();
                    gamePage.setLocationRelativeTo(this);
                    gamePage.setVisible(true);
                    this.dispose();
                } else {
                    usernameField.setBorder(BorderFactory.createLineBorder(Color.RED));
                    titleLabel.setForeground(Color.RED);
                    JOptionPane.showMessageDialog(this, "Please enter a username");
                }
            } else {
                gamePage.toFront(); // Optionally bring the existing window to front
            }
        }
    }
}
