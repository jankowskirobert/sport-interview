package scoreboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import scoreboard.GameErrors.GameIsAlreadyInProgress;
import scoreboard.GameErrors.TeamNamesCannotBeNull;

import static org.junit.jupiter.api.Assertions.*;

class FootballWorldCupScoreBoardTest {


    @Test
    public void testShouldNotStartAMatch_teamNamesAreNull() {
        //given
        FootballWorldCupScoreBoard scoreBoard = new FootballWorldCupScoreBoard(new InMemoryGamesRepository());

        //then
        assertThrows(TeamNamesCannotBeNull.class, () -> scoreBoard.startGame(null, null));
    }


    @Test
    public void testShouldNotStartAMatch_awayTeamIsNull() {
        //given
        FootballWorldCupScoreBoard scoreBoard = new FootballWorldCupScoreBoard(new InMemoryGamesRepository());
        TeamName homeTeam = TeamName.of("Mexico");

        //then
        assertThrows(TeamNamesCannotBeNull.class, () -> scoreBoard.startGame(homeTeam, null));
    }

    @Test
    public void testShouldNotStartAMatch_homeTeamIsNull() {
        //given
        FootballWorldCupScoreBoard scoreBoard = new FootballWorldCupScoreBoard(new InMemoryGamesRepository());
        TeamName awayTeam = TeamName.of("Canada");

        //then
        assertThrows(TeamNamesCannotBeNull.class, () -> scoreBoard.startGame(null, awayTeam));
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
        assertFalse(gamesRepository.hasGame(homeTeam, awayTeam));
    }

}
