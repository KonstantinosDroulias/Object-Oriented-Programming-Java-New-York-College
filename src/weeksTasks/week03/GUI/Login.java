package weeksTasks.week03.GUI;

import weeksTasks.week03.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener {

    JButton loginButton;
    AdminPage adminPage = null;
    JTextField usernameField;
    JPasswordField passwordField;

    public Login() {
        this.setTitle("Login");
        this.setSize(250, 350);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JLabel loginLabel = new JLabel("Admin Login");
        loginLabel.setBackground(new Color(0x006A4E));
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setOpaque(true);
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(5, 1, 10, 2));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        JLabel usernameLabel = new JLabel("Username");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();

        JPanel loginButtonPanel = new JPanel();
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        loginButtonPanel.add(loginButton);

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButtonPanel);

        JPanel warningPanel = new JPanel();
        JLabel loginWarning = new JLabel();
        loginWarning.setText("*Only admins of the store can login");
        loginWarning.setForeground(Color.WHITE);
        loginWarning.setHorizontalAlignment(SwingConstants.CENTER);
        warningPanel.add(loginWarning);
        warningPanel.setBorder(BorderFactory.createEmptyBorder(12, 10, 12, 10));
        warningPanel.setBackground(new Color(0x006A4E));

        this.add(loginLabel, BorderLayout.NORTH);
        this.add(loginPanel, BorderLayout.CENTER);
        this.add(warningPanel, BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = passwordField.getText();

            DBConnection db = new DBConnection();
            try {
                Connection conn = db.getConnection();

                String query = "SELECT * FROM bookstoreadmins WHERE username = ? AND adminPass = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, username);  // replaces first ?
                stmt.setString(2, password);  // replaces second ?

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    System.out.println("Login successful!");
                    if (adminPage == null || !adminPage.isDisplayable()) {
                        AdminPage adminPage = new AdminPage();
                        adminPage.setLocationRelativeTo(Login.this);
                        adminPage.setVisible(true);
                        this.dispose();
                    } else {
                        adminPage.toFront();
                    }
                } else {
                    System.out.println("Invalid username or password.");
                    JOptionPane.showMessageDialog(this, "Invalid username or password.");
                }
            } catch (SQLException d) {
                System.out.println("Database error: " + d.getMessage());
                d.printStackTrace();
            }
        }
    }

}
