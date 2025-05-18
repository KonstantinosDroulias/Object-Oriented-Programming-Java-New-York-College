package finalAssigments.HangMan.View;

import finalAssigments.HangMan.Model.UserModel;
import finalAssigments.HangMan.View.components.MyButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static finalAssigments.HangMan.HangMan.jaroFont;

public class MenuView extends JPanel {
    private UserModel user;
    private MyButton newGameButton;
    private MyButton createAccountButton;
    private MyButton loginButton;
    private JPanel accountButtons;
    private JLabel userLabel;
    private JLabel scoreLabel;

    public MenuView(UserModel user) {
        this.user = user;

        this.setLayout(new BorderLayout());

        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Hang Man Game");
        title.setFont(jaroFont.deriveFont(Font.BOLD, 62f));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        newGameButton = new MyButton("New Game");
        newGameButton.setEnabled(user != null);
        newGameButton.setBackground(Color.WHITE);
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        accountButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JPanel newAccountWrapper = new JPanel();
        newAccountWrapper.setLayout(new BoxLayout(newAccountWrapper, BoxLayout.Y_AXIS));
        JLabel infoText = new JLabel("New Here?");
        infoText.setAlignmentX(Component.LEFT_ALIGNMENT);
        createAccountButton = new MyButton("Create Account");
        createAccountButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        newAccountWrapper.add(infoText);
        newAccountWrapper.add(createAccountButton);

        JPanel loginWrapper = new JPanel();
        loginWrapper.setLayout(new BoxLayout(loginWrapper, BoxLayout.Y_AXIS));
        JLabel empty = new JLabel(" ");
        empty.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginButton = new MyButton("Login");
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginWrapper.add(empty);
        loginWrapper.add(loginButton);

        accountButtons.add(newAccountWrapper);
        accountButtons.add(Box.createHorizontalStrut(2));
        accountButtons.add(loginWrapper);

        menu.add(Box.createVerticalGlue());
        menu.add(title);
        menu.add(Box.createVerticalStrut(20));
        menu.add(newGameButton);
        menu.add(Box.createVerticalStrut(6));
        if (user == null) {
            menu.add(accountButtons);
        }
        menu.add(Box.createVerticalGlue());

        this.add(menu, BorderLayout.CENTER);
    }

    public void updateForUser(UserModel user) {
        this.user = user;
        newGameButton.setEnabled(true);
        if (accountButtons != null) {
            accountButtons.setVisible(false);
        }
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getCreateAccountButton() {
        return createAccountButton;
    }

    public MyButton getNewGameButton() {
        return newGameButton;
    }
}
