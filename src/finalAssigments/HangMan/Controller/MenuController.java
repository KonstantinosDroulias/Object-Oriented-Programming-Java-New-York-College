package finalAssigments.HangMan.Controller;

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

    MenuController(MenuView menuView, UserModel user, MainFrame frame) {
        this.menuView = menuView;
        this.user = user;
        this.frame = frame;

        goToGame();
    }

    private void goToGame() {
        menuView.getNewGameButton().addActionListener(e -> {
            if (user != null) {
                GameModel game = new GameModel();
                GameView gameView = new GameView(user);
                frame.addView(gameView, "Game");
                new GameController(game, gameView.getBottomPanel().getKeyboardPanel());
                game.startGame();
                frame.showView("Game");
            } else {
                JOptionPane.showMessageDialog(frame, "Error, Must login to start Game", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

}
