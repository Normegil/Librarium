package be.normegil.librarium.model.data;

import org.junit.Before;
import org.junit.Test;

import static be.normegil.librarium.AssertHelper.assertDifferentInstance;

public class GameTestImmutable {

    public static final long DEFAULT_ID = 0L;
    public static final String DEFAULT_NAME = "Name";
    public static final String ALTERNATIVE_NAME = "AlternativeName";
    private Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game(DEFAULT_ID, DEFAULT_NAME);
    }

    @Test
    public void testWithName() throws Exception {
        Game newGame = game.withName(ALTERNATIVE_NAME);
        assertDifferentInstance(game, newGame);
    }
}
