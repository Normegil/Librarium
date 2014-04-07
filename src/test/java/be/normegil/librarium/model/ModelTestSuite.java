package be.normegil.librarium.model;

import be.normegil.librarium.model.data.DataTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DataTestSuite.class
})
public class ModelTestSuite {
}
