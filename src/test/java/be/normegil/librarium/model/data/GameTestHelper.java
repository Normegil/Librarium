package be.normegil.librarium.model.data;

import org.apache.commons.lang3.Validate;

public class GameTestHelper {

    public static final String LINE_END = "\n";
    public static final String INDENTATION = "\t";

    public static void assertGamePropertiesEquals(Game expectedGame, Game toTestGame) {
        Validate.notNull(expectedGame);
        Validate.notNull(toTestGame);

        boolean areEquals = true;
        StringBuilder errorMessage = new StringBuilder();

        Long expectedGameId = expectedGame.getId();
        Long toTestGameId = toTestGame.getId();
        if (!expectedGameId.equals(toTestGameId)) {
            areEquals = false;
            errorMessage.append("Different IDs").append(LINE_END)
                    .append(INDENTATION).append("Expected=").append(expectedGameId).append(LINE_END)
                    .append(INDENTATION).append("Actual=").append(toTestGameId).append(LINE_END)
                    .append(LINE_END);
        }

        String expectedGameName = expectedGame.getName();
        String toTestGameName = toTestGame.getName();
        if (!expectedGameName.equals(toTestGameName)) {
            areEquals = false;
            errorMessage.append("Different Names").append(LINE_END)
                    .append(INDENTATION).append("Expected=").append(expectedGameName).append(LINE_END)
                    .append(INDENTATION).append("Actual=").append(toTestGameName).append(LINE_END)
                    .append(LINE_END);
        }

        if (!areEquals) {
            throw new AssertionError(errorMessage.toString());
        }
    }

}
