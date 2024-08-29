package scoreboard;

import java.util.List;

public interface GamesRepository {
    boolean registerMatch(Game between);
    List<GameSummary> resultsOrderedByRecentScore();

    boolean finish(TeamName homeTeam, TeamName awayTeam);
}
