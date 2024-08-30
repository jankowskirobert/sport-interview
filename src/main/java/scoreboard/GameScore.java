package scoreboard;

import java.util.Objects;

public class GameScore {
    private final int homeTeamScore;
    private final int awayTeamScore;

    private GameScore(int homeTeamScore, int awayTeamScore) {
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    static GameScore is(int homeTeamScore, int awayTeamScore) {
        return new GameScore(homeTeamScore, awayTeamScore);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameScore gameScore = (GameScore) o;
        return homeTeamScore == gameScore.homeTeamScore && awayTeamScore == gameScore.awayTeamScore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeamScore, awayTeamScore);
    }
}
