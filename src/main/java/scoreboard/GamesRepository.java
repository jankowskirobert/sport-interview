package scoreboard;

import java.util.List;
import java.util.Optional;

public interface GamesRepository {
    boolean registerMatch(Game between);

    List<GameSummary> resultsOrderedByRecentScore();

    Optional<Game> getGame(TeamName homeTeamName, TeamName awayTeamName);

    boolean updateGame(Game game);

    boolean finish(TeamName homeTeam, TeamName awayTeam);
}
