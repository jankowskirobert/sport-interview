package scoreboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FootballWorldCupScoreBoardTest {


    @Test
    public void testShouldStartAMatch() {
        FootballWorldCupScoreBoard scoreBoard = new FootballWorldCupScoreBoard(new InMemoryGamesRepository());
        TeamName homeTeam = TeamName.of("Mexico");
        TeamName awayTeamName = TeamName.of("Canada");
        Assertions.assertTrue(scoreBoard.startGame(homeTeam, awayTeamName).isRight());

    }

}
