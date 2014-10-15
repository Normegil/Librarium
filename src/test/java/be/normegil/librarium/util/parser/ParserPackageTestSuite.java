package be.normegil.librarium.util.parser;

import be.normegil.librarium.model.data.people.PersonTestSuite;
import be.normegil.librarium.util.parser.adapter.AdapterPackageTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		CsvParserTestSuite.class,
		JacksonParserTestSuite.class,
		JAXBHelperTestSuite.class,
		PersonTestSuite.class,

		AdapterPackageTestSuite.class
})
public class ParserPackageTestSuite {
}