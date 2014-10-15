package be.normegil.librarium.util.parser.adapter.jaxb;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		LocalDateJAXBAdapterTestSuite.class,
		LocalDateTimeJAXBAdapterTestSuite.class,
		URLJAXBAdapterTestSuite.class,
		UUIDToRESTURLJAXBAdapterTestSuite.class
})
public class JAXBAdapterPackageTestSuite {
}