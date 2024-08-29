package scoreboard;

public class Game {
    private final TeamName homeTeam;
    private final TeamName awayTeam;

    public Game(TeamName homeTeam, TeamName awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public static Game between(TeamName homeTeam, TeamName awayTeam) {
        return new Game(homeTeam, awayTeam);
    }

    public TeamName homeTeam() {
        return homeTeam;
    }
    public TeamName awayTeam() {
        return awayTeam;
    }
}
