package scoreboard;

import java.util.Objects;

public class GameSummary {
    private final Score homeTeamScore;
    private final Score awayTeamScore;

    private GameSummary(Score homeTeamScore, Score awayTeamScore) {
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    public static GameSummary of(Score homeTeamScore, Score awayTeamScore) {
        return new GameSummary(homeTeamScore, awayTeamScore);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameSummary that = (GameSummary) o;
        return homeTeamScore.equals(that.homeTeamScore) && awayTeamScore.equals(that.awayTeamScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeamScore, awayTeamScore);
    }

    @Override
    public String toString() {
        return "GameSummary{" +
                "homeTeamScore=" + homeTeamScore +
                ", awayTeamScore=" + awayTeamScore +
                '}';
    }
}
