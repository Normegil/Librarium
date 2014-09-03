package be.normegil.librarium.util.persistence;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTLocalDatePersistenceConverterSafety.class,
		UTLocalDatePersistenceConverter.class
})
public class LocalDatePersistenceConverterTestSuite {
}