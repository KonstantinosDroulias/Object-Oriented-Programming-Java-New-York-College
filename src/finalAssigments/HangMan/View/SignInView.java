package finalAssigments.HangMan.View;

import finalAssigments.HangMan.View.components.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SignInView extends JPanel{
    private JButton loginBack;
    private MyButton loginButton;
    private JPasswordField loginPasswordText;
    private JTextField loginUserNameText;

    public SignInView() {
        // LOGIN PANEL START ******
        this.setLayout(new GridBagLayout());
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

        loginBack = new JButton("← Back");
        loginBack.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel loginUserName = new JLabel("User Name:");
        loginUserName.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginUserNameText = new JTextField();
        loginUserNameText.setPreferredSize(new Dimension(220, 30));
        loginUserNameText.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel loginPasswordLabel = new JLabel("Password:");
        loginPasswordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginPasswordText = new JPasswordField();
        loginPasswordText.setPreferredSize(new Dimension(220, 30));
        loginPasswordText.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginButton = new MyButton("Login");
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginButton.addActionListener(e -> {});

        loginPanel.add(Box.createVerticalGlue());
        loginPanel.add(loginBack);
        loginPanel.add(Box.createVerticalStrut(18));
        loginPanel.add(loginUserName);
        loginPanel.add(loginUserNameText);
        loginPanel.add(loginPasswordLabel);
        loginPanel.add(loginPasswordText);
        loginPanel.add(Box.createVerticalStrut(8));
        loginPanel.add(loginButton);
        loginPanel.add(Box.createVerticalGlue());

        this.add(loginPanel);
        // LOGIN PANEL END ******
    }

    public void setLoginBack(ActionListener actionListener) {
        loginBack.addActionListener(actionListener);
    }

    public void setLoginListener(ActionListener actionListener) {
        loginButton.addActionListener(actionListener);
    }

    public String getLoginUserName() {
        return loginUserNameText.getText();
    }
    public String getLoginPassword() {
        return new String(loginPasswordText.getPassword());
    }
}
