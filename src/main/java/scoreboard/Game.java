package scoreboard;

import java.time.LocalDateTime;

public class Game {
    private final TeamName homeTeam;
    private final TeamName awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;
    private LocalDateTime updatedAt;

    private Game(TeamName homeTeam, TeamName awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = 0;
        this.awayTeamScore = 0;
        this.updatedAt = LocalDateTime.now();
    }

    static Game between(TeamName homeTeam, TeamName awayTeam) {
        return new Game(homeTeam, awayTeam);
    }

    TeamName homeTeamName() {
        return homeTeam;
    }

    TeamName awayTeamName() {
        return awayTeam;
    }

    GameScore gameScore() {
        return GameScore.is(homeTeamScore, awayTeamScore);
    }

    public void newScore(int homeTeamScore, int awayTeamScore) {
        if (homeTeamScore < 0 || awayTeamScore < 0) {
            throw new IllegalScoreException();
        } else {
            this.homeTeamScore = homeTeamScore;
            this.awayTeamScore = awayTeamScore;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public GameSummary toSummary() {
        return GameSummary.of(
                Score.forTeam(homeTeam).currentScore(homeTeamScore),
                Score.forTeam(awayTeam).currentScore(awayTeamScore)
        );
    }

    public int getScoreSum() {
        return homeTeamScore + awayTeamScore;
    }

    public LocalDateTime getLastUpdate() {
        return updatedAt;
    }
}
