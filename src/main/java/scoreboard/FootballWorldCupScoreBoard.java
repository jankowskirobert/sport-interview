package scoreboard;

import java.util.ArrayList;
import java.util.List;

public class FootballWorldCupScoreBoard {

    private final GamesRepository gamesRepository;

    FootballWorldCupScoreBoard(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    public Game startGame(TeamName homeTeam, TeamName awayTeam) {
        if (homeTeam == null || awayTeam == null) {
            throw GameErrors.NULLS_NOT_ALLOWED;
        }
        Game aGame = Game.between(homeTeam, awayTeam);
        boolean registeredMatch = gamesRepository.registerMatch(aGame);
        if (registeredMatch) {
            return aGame;
        } else {
            throw GameErrors.ALREADY_IN_MATCH;
        }
    }

    public void finishGame(TeamName homeTeam, TeamName awayTeam) {
        boolean finish = gamesRepository.finish(homeTeam, awayTeam);
        if(!finish) {
            throw GameErrors.MATCH_NOT_FOUND;
        }
    }


    public void updateScore(Score homeTeam, Score awayTeam) {

    }

    public List<GameSummary> getSummary() {
        return new ArrayList<>();
    }
}
