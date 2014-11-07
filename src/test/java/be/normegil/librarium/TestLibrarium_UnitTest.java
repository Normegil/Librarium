package be.normegil.librarium;

import be.normegil.librarium.libraries.LibrariesPackageTestSuite;
import be.normegil.librarium.model.ModelTestSuite;
import be.normegil.librarium.util.UtilPackageTestSuite;
import be.normegil.librarium.validation.ValidationPackageTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		LibrariesPackageTestSuite.class,
		UtilPackageTestSuite.class,
		ModelTestSuite.class,
		ValidationPackageTestSuite.class
})
public class TestLibrarium_UnitTest {

}
