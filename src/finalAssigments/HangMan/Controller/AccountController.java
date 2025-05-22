package finalAssigments.HangMan.Controller;

import finalAssigments.HangMan.Model.DAO.UserDAO;
import finalAssigments.HangMan.Model.UserModel;
import finalAssigments.HangMan.View.*;

import javax.swing.*;

public class AccountController {
    private UserDAO userDAO;
    private MainFrame frame;
    private MenuView mainMenuView;
    private SignInView signInView;
    private RegisterView registerView;
    private UserModel user;

    public AccountController(UserDAO userDAO, MainFrame frame, MenuView mainMenuView, SignInView signInView, RegisterView registerView, UserModel userModel) {
        this.userDAO = userDAO;
        this.frame = frame;
        this.mainMenuView = mainMenuView;
        this.signInView = signInView;
        this.registerView = registerView;
        this.user = userModel;


        // Register views in the CardLayout
        frame.addView(mainMenuView, "MainMenu");
        frame.addView(signInView, "Login");
        frame.addView(registerView, "Register");

        // Set initial screen
        frame.showView("MainMenu");

        initListeners();
    }

    private void initListeners() {
        // MAIN MENU → Navigate to login/register
        mainMenuView.getLoginButton().addActionListener(e -> frame.showView("Login"));
        mainMenuView.getCreateAccountButton().addActionListener(e -> frame.showView("Register"));

        // LOGIN flow
        signInView.setLoginListener(e -> {
            String username = signInView.getLoginUserName();
            String password = signInView.getLoginPassword();

            this.user = userDAO.login(username, password);
            if (user != null) {
                mainMenuView.updateForUser(user); // unlock New Game, hide buttons
                new MenuController(mainMenuView, user, frame, userDAO);
                frame.showView("MainMenu");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        signInView.setLoginBack(e -> frame.showView("MainMenu"));

        // REGISTER flow
        registerView.setCreateAccountButton(e -> {
            String username = registerView.getUsernameInput();
            String password = registerView.getPasswordInput();

            this.user = userDAO.register(username, password);
            if (user != null) {
                mainMenuView.updateForUser(user);
                new MenuController(mainMenuView, user, frame, userDAO);
                frame.showView("MainMenu");
            } else {
                JOptionPane.showMessageDialog(frame, "Username already taken or error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerView.setCreateAccountBack(e -> frame.showView("MainMenu"));
    }
}
