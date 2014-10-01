package be.normegil.librarium.libraries;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		URLTestSuite.class,
		ClassWrapperTestSuite.class,
		FieldWrapperTestSuite.class
})
public class LibrariesPackageTestSuite {
}