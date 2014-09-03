package be.normegil.librarium.util;

import be.normegil.librarium.util.jaxb.JAXBPackageTestSuite;
import be.normegil.librarium.util.persistence.PersistencePackageTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		ClassHelperTestSuite.class,
		CollectionComparatorTestSuite.class,
		DateHelperTestSuite.class,
		InputStreamHelperTestSuite.class,
        XSDUtilTestSuite.class,

		JAXBPackageTestSuite.class,
		PersistencePackageTestSuite.class
})
public class UtilPackageTestSuite {
}
