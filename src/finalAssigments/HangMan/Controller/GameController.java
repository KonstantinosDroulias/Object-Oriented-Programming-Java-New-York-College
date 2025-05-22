package finalAssigments.HangMan.Controller;

import finalAssigments.HangMan.Model.GameModel;
import finalAssigments.HangMan.Model.UserModel;
import finalAssigments.HangMan.View.GameView;
import finalAssigments.HangMan.View.KeyboardPanel;
import finalAssigments.HangMan.Model.DAO.UserDAO;

import javax.swing.*;

public class GameController {
    private GameModel game;
    private KeyboardPanel keyboard;
    private GameView gameView;
    private UserModel user;
    private UserDAO userDAO;

    public GameController(GameModel game, KeyboardPanel keyboard, GameView view, UserModel userModel, UserDAO userDAO) {
        this.game = game;
        this.user = userModel;
        this.keyboard = keyboard;
        this.gameView = view;
        this.userDAO = userDAO;

        game.getWordLetters();
        keyboard.addKeyListener(e -> {
            JButton clickedKey = (JButton) e.getSource();
            clickedKey.setEnabled(false);

            char letter = clickedKey.getText().toLowerCase().charAt(0);

            if (game.getLettersUsed().contains(letter)) {
                return;
            }

            boolean correct = game.checkLetter(letter);
            gameView.setSecretWord(game.getRevealedWord());

            gameView.getUsedLettersView().updateUsedLetters();

            if (!correct) {
                game.decrementLives();
                gameView.hangPart();
            }
            if (game.getRevealedWord().equalsIgnoreCase(game.getWord())) {
                keyboard.disableAllKeys();
                user.incrementScore();
                userDAO.updateScore(user);
                gameView.showEndScreen("🎉 You won!", () -> {
                    game.startGame();
                    char first = game.getWord().charAt(0);
                    char last = game.getWord().charAt(game.getWord().length() - 1);

                    keyboard.disableKey(first);
                    keyboard.disableKey(last);

                    gameView.setSecretWord(game.getRevealedWord());
                    gameView.updateScoreView(user.getScore());
                    keyboard.resetAllKeys();
                });
            }

            if (game.getLives() <= 0) {
                keyboard.disableAllKeys();
                gameView.showEndScreen("💀 You lost! The word was: " + game.getWord(), () -> {
                    game.startGame();
                    char first = game.getWord().charAt(0);
                    char last = game.getWord().charAt(game.getWord().length() - 1);

                    keyboard.disableKey(first);
                    keyboard.disableKey(last);
                    gameView.setSecretWord(game.getRevealedWord());
                    keyboard.resetAllKeys();
                });
            }
        });
    }
}
