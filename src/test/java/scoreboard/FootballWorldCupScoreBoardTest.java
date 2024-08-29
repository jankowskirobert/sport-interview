package scoreboard;

import org.junit.jupiter.api.Test;
import scoreboard.GameErrors.GameIsAlreadyInProgress;
import scoreboard.GameErrors.TeamNamesCannotBeNull;

import static org.junit.jupiter.api.Assertions.*;

class FootballWorldCupScoreBoardTest {


    @Test
    public void testShouldNotStartAMatch_teamNamesAreNull() {
        //given
        FootballWorldCupScoreBoard scoreBoard = aNewScoreBoard();

        //then
        assertThrows(TeamNamesCannotBeNull.class, () -> scoreBoard.startGame(null, null));
    }


    @Test
    public void testShouldNotStartAMatch_awayTeamIsNull() {
        //given
        FootballWorldCupScoreBoard scoreBoard = aNewScoreBoard();
        TeamName homeTeam = TeamName.of("Mexico");

        //then
        assertThrows(TeamNamesCannotBeNull.class, () -> scoreBoard.startGame(homeTeam, null));
    }

    @Test
    public void testShouldNotStartAMatch_homeTeamIsNull() {
        //given
        FootballWorldCupScoreBoard scoreBoard = aNewScoreBoard();
        TeamName awayTeam = TeamName.of("Canada");

        //then
        assertThrows(TeamNamesCannotBeNull.class, () -> scoreBoard.startGame(null, awayTeam));
    }

    @Test
    public void testShouldStartAMatch_gameShouldHaveInitialScore0to0() {
        //given
        FootballWorldCupScoreBoard scoreBoard = aNewScoreBoard();
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
        FootballWorldCupScoreBoard scoreBoard = aNewScoreBoard();
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
        FootballWorldCupScoreBoard scoreBoard = aNewScoreBoard();
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
        FootballWorldCupScoreBoard scoreBoard = aNewScoreBoard();
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

    }

    private FootballWorldCupScoreBoard aNewScoreBoard() {
        return new FootballWorldCupScoreBoard(new InMemoryGamesRepository());
    }

}
