package scoreboard;

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
        if (homeTeam == null || awayTeam == null) {
            throw GameErrors.NULLS_NOT_ALLOWED;
        }
        boolean finish = gamesRepository.finish(homeTeam, awayTeam);
        if (!finish) {
            throw GameErrors.MATCH_NOT_FOUND;
        }
    }


    public void updateScore(Score homeTeamScore, Score awayTeamScore) {
        if (homeTeamScore == null || awayTeamScore == null) {
            throw GameErrors.NULLS_NOT_ALLOWED;
        }
        Game game = gamesRepository.getGame(homeTeamScore.getTeamName(), awayTeamScore.getTeamName()).orElseThrow(
                () -> GameErrors.MATCH_NOT_FOUND
        );
        game.newScore(homeTeamScore.getValue(), awayTeamScore.getValue());
        gamesRepository.updateGame(game);
    }

    public List<GameSummary> getSummary() {
        return gamesRepository.resultsOrderedByRecentScore();
    }
}
