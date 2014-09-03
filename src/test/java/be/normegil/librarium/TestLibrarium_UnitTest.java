package be.normegil.librarium;

import be.normegil.librarium.libraries.LibrariesPackageTestSuiteTestSuite;
import be.normegil.librarium.model.ModelTestSuite;
import be.normegil.librarium.rest.RESTTestSuite;
import be.normegil.librarium.util.UtilPackageTestSuite;
import be.normegil.librarium.validation.ValidationPackageTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		LibrariesPackageTestSuiteTestSuite.class,
        UtilPackageTestSuite.class,
        ModelTestSuite.class,
        RESTTestSuite.class,
		ValidationPackageTestSuite.class
})
public class TestLibrarium_UnitTest {

}
