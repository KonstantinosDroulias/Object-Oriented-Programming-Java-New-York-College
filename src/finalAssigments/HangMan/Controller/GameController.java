package finalAssigments.HangMan.Controller;

import finalAssigments.HangMan.Model.GameModel;
import finalAssigments.HangMan.View.KeyboardPanel;

import javax.swing.*;

public class GameController {
    private GameModel game;
    private KeyboardPanel keyboard;

    public GameController(GameModel game, KeyboardPanel keyboard) {
        this.game = game;
        this.keyboard = keyboard;

        game.getWordLetters();
        keyboard.addKeyListener(e -> {
            JButton clickedKey = (JButton) e.getSource();
            clickedKey.setEnabled(false);
            char letter = clickedKey.getText().toLowerCase().charAt(0);
            game.addClickedLetters(letter);
            game.checkLetter(letter);
        });
    }
}
