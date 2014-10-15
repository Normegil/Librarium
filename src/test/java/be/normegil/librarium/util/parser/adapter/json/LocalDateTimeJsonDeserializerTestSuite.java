package be.normegil.librarium.util.parser.adapter.json;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTLocalDateTimeJsonDeserializerSafety.class,
		UTLocalDateTimeJsonDeserializer.class
})
public class LocalDateTimeJsonDeserializerTestSuite {
}