package weeksTasks.week02;

public class Team {
    private String name;
    private int wins;
    private int losses;
    private int draws;

    public Team(String name, int wins, int losses, int draws) {
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
    }

    public String getName() {
        return name;
    }
    public int getWins() {
        return wins;
    }
    public int getLosses() { return losses; }
    public int getDraws() { return draws; }

    public void setWins() { this.wins += 1; }
    public void setLosses() { this.losses += 1; }
    public void setDraws() { this.draws += 1; }
}
