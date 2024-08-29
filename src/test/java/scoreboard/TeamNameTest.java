package scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamNameTest {

    @Test
    public void testThatTeamNameCannotBeNull() {
        assertThrows(TeamNameIllegalNameException.class, () -> TeamName.of(null));
    }

    @Test
    public void testThatTeamNameCannotBeBlank() {
        assertThrows(TeamNameIllegalNameException.class, () -> TeamName.of(""));
    }

}
