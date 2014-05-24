package be.normegil.librarium.model.data;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UTGameSafety.class,
        GameTest.class,
        UTGame_XmlSupport.class,
        UTGame_DatabaseSupport.class
})
public class GameTestSuite {
}
