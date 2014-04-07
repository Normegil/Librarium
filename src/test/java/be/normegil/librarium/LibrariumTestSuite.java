package be.normegil.librarium;

import be.normegil.librarium.model.ModelTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AssertHelperTestSuite.class,
        ModelTestSuite.class
})
public class LibrariumTestSuite {
}
