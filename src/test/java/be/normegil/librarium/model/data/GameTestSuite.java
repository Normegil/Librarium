package be.normegil.librarium.model.data;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        GameTestSafety.class,
        GameTestImmutable.class,
        GameTest.class
})
public class GameTestSuite {
}
