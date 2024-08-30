package scoreboard;

import java.util.Objects;

public class Score {


    private final TeamName teamName;
    private final int score;

    private Score(TeamName teamName, int score) {
        this.teamName = teamName;
        this.score = score;
    }

    public static ScoreBuilder forTeam(TeamName teamName) {
        return new ScoreBuilder(teamName);
    }

    public TeamName getTeamName() {
        return teamName;
    }

    public int getValue() {
        return score;
    }


    static class ScoreBuilder {
        TeamName teamName;

        public ScoreBuilder(TeamName teamName) {
            this.teamName = teamName;
        }

        Score currentScore(int score) {
            return new Score(teamName, score);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score && teamName.equals(score1.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamName, score);
    }

    @Override
    public String toString() {
        return "Score{" +
                "teamName=" + teamName.asString() +
                ", score=" + score +
                '}';
    }
}
