package scoreboard;

import java.util.List;
import java.util.Optional;

public interface GamesRepository {
    boolean registerMatch(Game between);
    List<GameSummary> resultsOrderedByRecentScore();
    public Optional<Game> getGame(TeamName homeTeamName, TeamName awayTeamName);
    public boolean updateGame(Game game);
    boolean finish(TeamName homeTeam, TeamName awayTeam);
}
