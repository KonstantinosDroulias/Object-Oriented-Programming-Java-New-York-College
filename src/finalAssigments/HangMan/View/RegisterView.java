package finalAssigments.HangMan.View;

import finalAssigments.HangMan.View.components.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegisterView extends JPanel{
    private JButton createAccountBack;
    private MyButton createAccountButton;
    private JTextField createUserNameText;
    private JPasswordField createPasswordText;


    public RegisterView() {
        // CREATE ACCOUNT PANEL START ******
        this.setLayout(new GridBagLayout());
        JPanel createAccountPanel = new JPanel();
        createAccountPanel.setLayout(new BoxLayout(createAccountPanel, BoxLayout.Y_AXIS));

        createAccountBack = new JButton("← Back");
        createAccountBack.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel createUserNameLabel = new JLabel("User Name:");
        createUserNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        createUserNameText = new JTextField();
        createUserNameText.setPreferredSize(new Dimension(220, 30));
        createUserNameText.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel createPasswordLabel = new JLabel("Password:");
        createPasswordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        createPasswordText = new JPasswordField();
        createPasswordText.setPreferredSize(new Dimension(220, 30));
        createPasswordText.setAlignmentX(Component.LEFT_ALIGNMENT);
        String passwordNotice = ""; // TODO: REMOVE HARD CODED VALUE
        JLabel passwordErrorLabel = new JLabel(passwordNotice);
        passwordErrorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        createAccountButton = new MyButton("Create Account");
        createAccountButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        createAccountButton.addActionListener(e -> {});

        createAccountPanel.add(Box.createVerticalGlue());
        createAccountPanel.add(createAccountBack);
        createAccountPanel.add(Box.createVerticalStrut(18));
        createAccountPanel.add(createUserNameLabel);
        createAccountPanel.add(createUserNameText);
        createAccountPanel.add(createPasswordLabel);
        createAccountPanel.add(createPasswordText);
        createAccountPanel.add(passwordErrorLabel);
        createAccountPanel.add(Box.createVerticalStrut(8));
        createAccountPanel.add(createAccountButton);
        createAccountPanel.add(Box.createVerticalGlue());

        this.add(createAccountPanel);
        // CREATE ACCOUNT PANEL END ******
    }

    public void setCreateAccountBack(ActionListener actionListener){
        createAccountBack.addActionListener(actionListener);
    }

    public void setCreateAccountButton(ActionListener actionListener){
        createAccountButton.addActionListener(actionListener);
    }

    public String getUsernameInput() {
        return createUserNameText.getText();
    }

    public String getPasswordInput() {
        return new String(createPasswordText.getPassword());
    }

}
