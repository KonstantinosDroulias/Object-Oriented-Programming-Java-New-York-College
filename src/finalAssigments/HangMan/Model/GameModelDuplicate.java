package finalAssigments.HangMan.Model;

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

public class GameModelDuplicate {
    private UserModel user;
    private String word;
    private int Lives;
    private ArrayList<Character> wordLetters = new ArrayList<>();
    private ArrayList<Character> lettersUsed = new ArrayList<>();
    private GameView gameView;
    private boolean gameOutcome;
    private String secretWord;

    public GameModelDuplicate() {

    }

    public void startGame() {
        this.Lives = 6;
        if (getWordApi() == null) {
            JOptionPane.showMessageDialog(gameView, "Error loading word. Please wait for a moment and restart the game.", "Error", JOptionPane.ERROR_MESSAGE);
            // In the course I took from youtube they talked about threads but I need to give more time to them.
            // So below code for the timer is from chatgpt.
            new javax.swing.Timer(2000, e -> System.exit(0)).start();
        } else {
            this.word = getWordApi(); // sets the word
        }
        this.secretWord = constructSecretWord(); // builds initial secretWord
    }

    public String getWordApi() {
        word = null;
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
        }
        wordLetters.clear();
        for (int i = 0; i < word.length(); i++) {
            char letter = Character.toLowerCase(word.charAt(i));
            if (!wordLetters.contains(letter)) {
                wordLetters.add(letter);
            }
        }
        return word;
    }

    public ArrayList<Character> getWordLetters() {
        return wordLetters;
    }

    public String constructSecretWord() {
        lettersUsed.clear();
        char first = Character.toLowerCase(word.charAt(0));
        char last = Character.toLowerCase(word.charAt(word.length() - 1));

        lettersUsed.add(first);
        if (first != last) {
            lettersUsed.add(last);
        }

        return buildRevealedWord();
    }

    public boolean checkLetter(char letter) {
        letter = Character.toLowerCase(letter);
        if (wordLetters.contains(letter) && !lettersUsed.contains(letter)) {
            lettersUsed.add(letter);
            secretWord = buildRevealedWord();
            return true;
        }
        return false;
    }

    public String buildRevealedWord() {
        String result = "";

        for (int i = 0; i < word.length(); i++) {
            char current = Character.toLowerCase(word.charAt(i));
            if (lettersUsed.contains(current)) {
                result += word.charAt(i);
            } else {
                result += "_";
            }
        }

        return result;
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

    public String getRevealedWord() {
        return secretWord;
    }

    public GameView getGameView() {
        return gameView;
    }
}

