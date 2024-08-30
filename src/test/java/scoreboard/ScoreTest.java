package scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {


    @Test
    public void testTwoSameScoresShouldBeEqual() {
        assertEquals(
                Score.forTeam(TeamName.of("Poland")).currentScore(4),
                Score.forTeam(TeamName.of("Poland")).currentScore(4)
        );

    }
}
