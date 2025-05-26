package finalAssigments.HangMan.Model;

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
    private JLabel livesLabel = new JLabel("6");
    private String secretWord;

    public GameModel() {}

    public void startGame() {
        this.Lives = 6;
        this.word = getWordApi();
        this.secretWord = constructSecretWord();
        livesLabel.setText("" + Lives);
    }

    public String getWordApi() {
        wordLetters.clear();
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
        System.out.println("Got word: " + word);
        for (int i = 0; i < word.length(); i++) {
            char letter = Character.toLowerCase(word.charAt(i));
            if (!wordLetters.contains(letter)) {
                wordLetters.add(letter);
            }
        }
        return word;
    }

    public String getWord() {
        return this.word;
    }

    public ArrayList<Character> getWordLetters() {
        return wordLetters;
    }

    public String constructSecretWord() {
        lettersUsed.clear();

        if (word == null || word.length() == 0) {
            return "";
        }

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
                result += word.charAt(i); // preserve original case
            } else {
                result += "_";
            }
        }

        return result;
    }

    public String getRevealedWord() {
        return secretWord;
    }

    public JLabel getLivesLabel() {
        return livesLabel;
    }

    public int getLives() {
        return this.Lives;
    }

    public void decrementLives() {
        this.Lives--;
        livesLabel.setText(" " + this.Lives);
    }

    public void resetLives() {
        this.Lives = 6;
        livesLabel.setText(" " + this.Lives);
    }


    public ArrayList<Character> getLettersUsed() {
        return lettersUsed;
    }

    public JPanel getGameView() {
        return null;
    }
}
