package scoreboard;

class TeamName {
    private final String teamName;

    private TeamName(String teamName) {
        this.teamName = teamName;
    }

    static TeamName of(String teamName) {
        if (teamName != null && !teamName.isBlank()) {
            return new TeamName(teamName);
        } else {
            throw new TeamNameIllegalNameException("Given Name is blank");
        }
    }

    String asString() {
        return teamName;
    }
}
