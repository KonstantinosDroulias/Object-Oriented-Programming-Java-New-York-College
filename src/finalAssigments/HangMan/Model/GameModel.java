package finalAssigments.HangMan.Model;

import finalAssigments.HangMan.Controller.GameController;
import finalAssigments.HangMan.View.GameView;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GameModel {
    private UserModel user;
    private String word;
    private int Lives;
    private ArrayList<Character> wordLetters = new ArrayList<>();
    private ArrayList<Character> lettersUsed = new ArrayList<>();
    private GameView gameView;
    private boolean gameOutcome;
    private String secretWord;

    public GameModel() {

    }

    public void startGame() {
        this.Lives = 6;
        this.word = getSecretWord();
    }

    public String getWordApi() {
        /*word = null;
        try {
            URL url = new URL("https://random-word-api.vercel.app/api?words=1&type=capitalized");
            InputStreamReader reader = new InputStreamReader(url.openStream());
            JSONParser parser = new JSONParser();
            JSONArray json = (JSONArray) parser.parse(reader);
            word = (String) json.get(0);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        } */
        word = "Test";
        for (int i = 0; i < word.length(); i++) {
            char letter = word.toLowerCase().charAt(i);
            wordLetters.add(letter);
        }
        return word;
    }

    public ArrayList<Character> getWordLetters() {
        return wordLetters;
    }

    public String getSecretWord() {
        if (getWordApi() == null) {
            JOptionPane.showMessageDialog(gameView, "Error loading word. Please wait for a moment and restart the game.", "Error", JOptionPane.ERROR_MESSAGE);
            // In the course I took from youtube they talked about threads but I need to give more time to them.
            // So below code for the timer is from chatgpt.
            new javax.swing.Timer(2000, e -> System.exit(0)).start();
        }
        char firstLetter = wordLetters.get(0);
        char lastLetter = wordLetters.get(wordLetters.size() -1);
        for (int i = 0; i < word.length(); i++) {
            if (word.toLowerCase().charAt(i) == firstLetter || word.toLowerCase().charAt(i) == lastLetter) {
                secretWord += word.charAt(i);
            } else {
                secretWord += "_";
            }
        }

        return secretWord;
    }

    public int getLives() {
        return this.Lives;
    }

    public void addClickedLetters(char letter) {
        lettersUsed.add(letter);
    }
    public ArrayList<Character> getLettersUsed() {
        return lettersUsed;
    }

    public void checkLetter(char letter) {
        if (wordLetters.contains(letter)) {
            for (int i = 0; i < word.length(); i++) {
                if (word.toLowerCase().charAt(i) == letter) {
                    secretWord += word.charAt(i);
                } else {
                    secretWord += "_";
                }
            }
            this.word = secretWord;
        }
    }


    public JPanel getGameView() {
        return gameView;
    }
}
