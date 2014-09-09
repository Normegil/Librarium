package be.normegil.librarium.rest.game;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UTGameREST_Safety.class,
        UTGameREST.class
})
public class GameRESTTestSuite {
}
