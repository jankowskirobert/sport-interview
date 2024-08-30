package scoreboard;

import java.util.Objects;

public class TeamName {
    private final String teamName;

    private TeamName(String teamName) {
        this.teamName = teamName;
    }

    public static TeamName of(String teamName) {
        if (teamName != null && !teamName.isBlank()) {
            return new TeamName(teamName);
        } else {
            throw new TeamNameIllegalNameException("Given Name is blank");
        }
    }

    String asString() {
        return teamName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamName teamName1 = (TeamName) o;
        return Objects.equals(teamName, teamName1.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamName);
    }
}
