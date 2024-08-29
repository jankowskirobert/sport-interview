package scoreboard;

public class TeamName {
    private final String teamName;

    public TeamName(String teamName) {
        this.teamName = teamName;
    }

    public static TeamName of(String teamName) {
        if (teamName != null && !teamName.isBlank()) {
            return new TeamName(teamName);
        } else {
            throw new TeamNameIllegalNameException("Given Name is blank");
        }
    }

    public String asString() {
        return teamName;
    }
}
