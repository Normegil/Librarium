package be.normegil.librarium.util.jaxb;

import be.normegil.librarium.util.jaxb.adapter.AdapterPackageTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		AdapterPackageTestSuite.class,

		JAXBHelperTestSuite.class
})
public class JAXBPackageTestSuite {
}