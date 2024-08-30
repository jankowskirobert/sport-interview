package scoreboard;

import org.junit.jupiter.api.Test;
import scoreboard.GameErrors.GameIsAlreadyInProgress;
import scoreboard.GameErrors.CannotBeNull;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FootballWorldCupScoreBoardTest {


    @Test
    public void testShouldNotStartAMatch_teamNamesAreNull() {
        //given
        FootballWorldCupScoreBoard scoreBoard = new FootballWorldCupScoreBoard(new InMemoryGamesRepository());

        //then
        assertThrows(CannotBeNull.class, () -> scoreBoard.startGame(null, null));
    }


    @Test
    public void testShouldNotStartAMatch_awayTeamIsNull() {
        //given
        FootballWorldCupScoreBoard scoreBoard = new FootballWorldCupScoreBoard(new InMemoryGamesRepository());
        TeamName homeTeam = TeamName.of("Mexico");

        //then
        assertThrows(CannotBeNull.class, () -> scoreBoard.startGame(homeTeam, null));
    }

    @Test
    public void testShouldNotStartAMatch_homeTeamIsNull() {
        //given
        FootballWorldCupScoreBoard scoreBoard = new FootballWorldCupScoreBoard(new InMemoryGamesRepository());
        TeamName awayTeam = TeamName.of("Canada");

        //then
        assertThrows(CannotBeNull.class, () -> scoreBoard.startGame(null, awayTeam));
    }

    @Test
    public void testShouldStartAMatch_gameShouldHaveInitialScore0to0() {
        //given
        FootballWorldCupScoreBoard scoreBoard = new FootballWorldCupScoreBoard(new InMemoryGamesRepository());
        TeamName homeTeam = TeamName.of("Mexico");
        TeamName awayTeam = TeamName.of("Canada");

        //when
        Game startedGame = scoreBoard.startGame(homeTeam, awayTeam);

        //then
        assertNotNull(startedGame);
        assertEquals(GameScore.is(0, 0), startedGame.gameScore());
    }


    @Test
    public void testShouldNotStartAMatch_thereIsAlreadyRegisteredMatchForTeams() {
        //given
        FootballWorldCupScoreBoard scoreBoard = new FootballWorldCupScoreBoard(new InMemoryGamesRepository());
        TeamName homeTeam = TeamName.of("Mexico");
        TeamName awayTeamName = TeamName.of("Canada");

        //when
        Game firstGame = scoreBoard.startGame(homeTeam, awayTeamName);

        //then
        assertNotNull(firstGame);
        assertThrows(GameIsAlreadyInProgress.class, () -> scoreBoard.startGame(homeTeam, awayTeamName));
    }

    @Test
    public void testShouldNotStartAMatch_thereIsAlreadyRegisteredMatchForHomeTeam() {
        //given
        FootballWorldCupScoreBoard scoreBoard = new FootballWorldCupScoreBoard(new InMemoryGamesRepository());
        TeamName homeTeam = TeamName.of("Mexico");
        TeamName awayTeamName = TeamName.of("Canada");
        TeamName anotherHomeTeamName = TeamName.of("Poland");

        //when
        Game firstGame = scoreBoard.startGame(homeTeam, awayTeamName);

        //then
        assertNotNull(firstGame);
        assertThrows(GameIsAlreadyInProgress.class, () -> scoreBoard.startGame(anotherHomeTeamName, awayTeamName));

    }

    @Test
    public void testShouldNotStartAMatch_thereIsAlreadyRegisteredMatchForAwayTeam() {
        //given
        FootballWorldCupScoreBoard scoreBoard = new FootballWorldCupScoreBoard(new InMemoryGamesRepository());
        TeamName homeTeam = TeamName.of("Mexico");
        TeamName awayTeamName = TeamName.of("Canada");
        TeamName anotherAwayTeamName = TeamName.of("Poland");

        //when
        Game firstGame = scoreBoard.startGame(homeTeam, awayTeamName);

        //then
        assertNotNull(firstGame);
        assertThrows(GameIsAlreadyInProgress.class, () -> scoreBoard.startGame(homeTeam, anotherAwayTeamName));
    }

    @Test
    public void testShouldFinishGameInProgress() {
        //given
        InMemoryGamesRepository gamesRepository = new InMemoryGamesRepository();
        FootballWorldCupScoreBoard scoreBoard = new FootballWorldCupScoreBoard(gamesRepository);
        TeamName homeTeam = TeamName.of("Mexico");
        TeamName awayTeam = TeamName.of("Canada");
        scoreBoard.startGame(homeTeam, awayTeam);

        //when
        scoreBoard.finishGame(homeTeam, awayTeam);

        //then
        assertFalse(gamesRepository.hasGame(homeTeam, awayTeam));
    }

    @Test
    public void testShouldUpdateGameScore() {
        //given
        InMemoryGamesRepository gamesRepository = new InMemoryGamesRepository();
        FootballWorldCupScoreBoard scoreBoard = new FootballWorldCupScoreBoard(gamesRepository);
        TeamName homeTeam = TeamName.of("Mexico");
        TeamName awayTeam = TeamName.of("Canada");
        scoreBoard.startGame(homeTeam, awayTeam);
        Score newScoreForHomeTeam = Score.forTeam(homeTeam).currentScore(1);
        Score newScoreForAwayTeam = Score.forTeam(awayTeam).currentScore(2);

        //when
        scoreBoard.updateScore(newScoreForHomeTeam, newScoreForAwayTeam);

        //then
        Optional<Game> game = gamesRepository.getGame(homeTeam, awayTeam);
        assertTrue(game.isPresent());
        assertEquals(GameScore.is(1, 2), game.get().gameScore());
    }

    /*
        a. Mexico - Canada: 0 – 5
        b. Spain - Brazil: 10 – 2
        c. Germany - France: 2 – 2
        d. Uruguay - Italy: 6 – 6
        e. Argentina - Australia: 3 - 1
     */
    @Test
    public void testShouldGetGameScoreInCorrectOrder() {
        //given
        InMemoryGamesRepository gamesRepository = new InMemoryGamesRepository();
        FootballWorldCupScoreBoard scoreBoard = new FootballWorldCupScoreBoard(gamesRepository);

        TeamName mexicoTeam = TeamName.of("Mexico");
        TeamName canadaTeam = TeamName.of("Canada");
        scoreBoard.startGame(mexicoTeam, canadaTeam);
        Score mexicoTeamScore = Score.forTeam(mexicoTeam).currentScore(0);
        Score canadaTeamScore = Score.forTeam(canadaTeam).currentScore(5);
        scoreBoard.updateScore(mexicoTeamScore, canadaTeamScore);

        TeamName spainTeam = TeamName.of("Spain");
        TeamName brazilTeam = TeamName.of("Brazil");
        scoreBoard.startGame(spainTeam, brazilTeam);
        Score spainTeamScore = Score.forTeam(spainTeam).currentScore(10);
        Score brazilTeamScore = Score.forTeam(brazilTeam).currentScore(2);
        scoreBoard.updateScore(spainTeamScore, brazilTeamScore);

        TeamName germanTeam = TeamName.of("Germany");
        TeamName franceTeam = TeamName.of("France");
        scoreBoard.startGame(germanTeam, franceTeam);
        Score germanTeamScore = Score.forTeam(germanTeam).currentScore(2);
        Score franceTeamScore = Score.forTeam(franceTeam).currentScore(2);
        scoreBoard.updateScore(germanTeamScore, franceTeamScore);

        TeamName uruguayTeam = TeamName.of("Uruguay");
        TeamName italyTeam = TeamName.of("Italy");
        scoreBoard.startGame(uruguayTeam, italyTeam);
        Score uruguayTeamScore = Score.forTeam(uruguayTeam).currentScore(6);
        Score italyTeamScore = Score.forTeam(italyTeam).currentScore(6);
        scoreBoard.updateScore(uruguayTeamScore, italyTeamScore);

        TeamName argentinaTeam = TeamName.of("Argentina");
        TeamName australiaTeam = TeamName.of("Australia");
        scoreBoard.startGame(argentinaTeam, australiaTeam);
        Score argentinaTeamScore = Score.forTeam(argentinaTeam).currentScore(3);
        Score australiaTeamScore = Score.forTeam(australiaTeam).currentScore(1);
        scoreBoard.updateScore(argentinaTeamScore, australiaTeamScore);

        //then
        List<GameSummary> summary = scoreBoard.getSummary();
        assertEquals(5, summary.size());
        assertEquals(GameSummary.of(uruguayTeamScore, italyTeamScore), summary.get(0));
        assertEquals(GameSummary.of(spainTeamScore, brazilTeamScore), summary.get(0));
        assertEquals(GameSummary.of(mexicoTeamScore, canadaTeamScore), summary.get(0));
        assertEquals(GameSummary.of(argentinaTeamScore, australiaTeamScore), summary.get(0));
        assertEquals(GameSummary.of(germanTeamScore, franceTeamScore), summary.get(0));
    }

}
