package scoreboard;

class GameErrors {
    static final GameIsAlreadyInProgress ALREADY_IN_MATCH = new GameIsAlreadyInProgress("Could not register match because it is already registered or team is already playing");
    static final CannotBeNull NULLS_NOT_ALLOWED = new CannotBeNull("Nulls are not allowed");
    static final CouldNotFinishMatch MATCH_NOT_FOUND = new CouldNotFinishMatch("Could not finish match, team/s not found");


    static class GameIsAlreadyInProgress extends RuntimeException {

        private GameIsAlreadyInProgress(String errorMessage) {
            super(errorMessage);
        }
    }

    static class CannotBeNull extends RuntimeException {

        private CannotBeNull(String errorMessage) {
            super(errorMessage);
        }
    }
    static class CouldNotFinishMatch extends RuntimeException {

        private CouldNotFinishMatch(String errorMessage) {
            super(errorMessage);
        }
    }
}
