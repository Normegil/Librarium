package be.normegil.librarium.util.parser.adapter.json;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		LocalDateTimeJsonDeserializerTestSuite.class,
		LocalDateTimeJsonSerializerTestSuite.class,
		ThrowableJsonDeserializerTestSuite.class,
		ThrowableJsonSerializerTestSuite.class,
		URLJsonDeserializerTestSuite.class,
		URLJsonSerializerTestSuite.class
})
public class JSONAdapterPackageTestSuite {
}