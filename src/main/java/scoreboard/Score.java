package scoreboard;

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


    static class ScoreBuilder {
        TeamName teamName;

        public ScoreBuilder(TeamName teamName) {
            this.teamName = teamName;
        }

        Score currentScore(int score) {
            return new Score(teamName, score);
        }
    }
}
