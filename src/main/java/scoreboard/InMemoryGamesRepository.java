package scoreboard;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryGamesRepository implements GamesRepository{

    Map<String, Game> homeTeam = new ConcurrentHashMap<>();
    Map<String, Game> awayTeam = new ConcurrentHashMap<>();

    @Override
    public Game registerMatch(Game game) {
        homeTeam.put(game.homeTeam().asString(), game);
        awayTeam.put(game.awayTeam().asString(), game);
        return game;
    }
}
