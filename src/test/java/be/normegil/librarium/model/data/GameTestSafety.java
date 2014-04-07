package be.normegil.librarium.model.data;

import org.junit.Test;

public class GameTestSafety {

    public static final long DEFAULT_ID = 0L;
    public static final String DEFAULT_NAME = "Name";
    public static final String EMPTY_NAME = "";

    @Test(expected = NullPointerException.class)
    public void testConstructor_NullId() throws Exception {
        new Game(null, DEFAULT_NAME);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructor_NullName() throws Exception {
        new Game(DEFAULT_ID, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_EmptyName() throws Exception {
        new Game(DEFAULT_ID, EMPTY_NAME);
    }

    @Test(expected = NullPointerException.class)
    public void testCopyConstructor_NullParameter() throws Exception {
        new Game(null);
    }

    @Test(expected = NullPointerException.class)
    public void testWithName_NullParameter() throws Exception {
        Game game = new Game(DEFAULT_ID, DEFAULT_NAME);
        game.withName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithName_EmptyParameter() throws Exception {
        Game game = new Game(DEFAULT_ID, DEFAULT_NAME);
        game.withName(EMPTY_NAME);
    }

}
