package finalAssigments.HangMan.Controller;

import finalAssigments.HangMan.Model.DAO.UserDAO;
import finalAssigments.HangMan.Model.GameModel;
import finalAssigments.HangMan.Model.UserModel;
import finalAssigments.HangMan.View.GameView;
import finalAssigments.HangMan.View.MainFrame;
import finalAssigments.HangMan.View.MenuView;

import javax.swing.*;

public class MenuController {
    private MenuView menuView;
    private UserModel user;
    private MainFrame frame;
    private UserDAO userDAO;

    MenuController(MenuView menuView, UserModel user, MainFrame frame, UserDAO userDAO) {
        this.menuView = menuView;
        this.user = user;
        this.frame = frame;
        this.userDAO = userDAO;

        goToGame();
    }

    private void goToGame() {
        menuView.getNewGameButton().addActionListener(e -> {
            if (user != null) {
                GameModel game = new GameModel();
                game.startGame();
                GameView gameView = new GameView(user, game);
                frame.addView(gameView, "Game");
                new GameController(game, gameView.getBottomPanel().getKeyboardPanel(), gameView, user, userDAO);
                frame.showView("Game");
            } else {
                JOptionPane.showMessageDialog(frame, "Error, Must login to start Game", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

}
