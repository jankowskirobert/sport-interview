package scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameSummaryTest {

    @Test
    public void testTwoSameGameSummaryShouldBeEqual() {
        TeamName uruguayTeam = TeamName.of("Uruguay");
        TeamName italyTeam = TeamName.of("Italy");
        Score uruguayTeamScore = Score.forTeam(uruguayTeam).currentScore(6);
        Score italyTeamScore = Score.forTeam(italyTeam).currentScore(6);
        assertEquals(GameSummary.of(uruguayTeamScore, italyTeamScore), GameSummary.of(uruguayTeamScore, italyTeamScore));
    }

}
