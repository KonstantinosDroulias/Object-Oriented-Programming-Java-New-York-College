package finalAssigments.HangMan.Model;

public class UserModel {
    private String username;
    private int score;

    public UserModel(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void incrementScore() {
        this.score += 1;
    }
}
