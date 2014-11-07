package be.normegil.librarium.util;

import be.normegil.librarium.util.parser.ParserPackageTestSuite;
import be.normegil.librarium.util.persistence.PersistencePackageTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		CollectionComparatorTestSuite.class,
		DateHelperTestSuite.class,
		InputStreamHelperTestSuite.class,
        XSDUtilTestSuite.class,

		ParserPackageTestSuite.class,
		PersistencePackageTestSuite.class
})
public class UtilPackageTestSuite {
}
