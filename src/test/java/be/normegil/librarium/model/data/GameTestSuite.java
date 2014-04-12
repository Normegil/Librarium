package be.normegil.librarium.model.data;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        GameTestSafety.class,
        GameTestImmutable.class,
        GameTest.class,
        GameTestXmlSupport.class
})
public class GameTestSuite {
}
