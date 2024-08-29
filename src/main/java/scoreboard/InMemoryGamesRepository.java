package scoreboard;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryGamesRepository implements GamesRepository {

    private final Map<String, Game> homeTeam = new ConcurrentHashMap<>();
    private final Map<String, Game> awayTeam = new ConcurrentHashMap<>();

    @Override
    public boolean registerMatch(Game game) {
        if (!homeTeam.containsKey(game.homeTeamName().asString()) && !awayTeam.containsKey(game.awayTeamName().asString())) {
            homeTeam.put(game.homeTeamName().asString(), game);
            awayTeam.put(game.awayTeamName().asString(), game);
            return true;
        }
        return false;
    }

    @Override
    public boolean finish(TeamName homeTeamName, TeamName awayTeamName) {
        if (homeTeam.containsKey(homeTeamName.asString()) && awayTeam.containsKey(awayTeamName.asString())) {
            homeTeam.remove(homeTeamName.asString());
            awayTeam.remove(awayTeamName.asString());
            return true;
        }
        return false;
    }

    @Override
    public List<GameSummary> resultsOrderedByRecentScore() {
        return null;
    }
}
