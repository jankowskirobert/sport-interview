package scoreboard;

import io.vavr.control.Either;

import java.util.ArrayList;
import java.util.List;

public class FootballWorldCupScoreBoard {

    private final GamesRepository gamesRepository;

    public FootballWorldCupScoreBoard(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    public Either<StartGameError, Game> startGame(TeamName homeTeam, TeamName awayTeam) {
        return Either.right(gamesRepository.registerMatch(Game.between(homeTeam, awayTeam)));
    }

    public void finishGame(Game game) {

    }


    public void updateScore(Score homeTeam, Score awayTeam) {

    }

    public List<GameSummary> getSummary() {
        return new ArrayList<>();
    }
}
