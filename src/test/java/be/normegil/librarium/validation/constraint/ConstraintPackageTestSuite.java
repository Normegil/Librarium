package be.normegil.librarium.validation.constraint;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		ExistingIDTestSuite.class,
		HttpUrlValidatorTestSuite.class,
		NotEmptyValidatorTestSuite.class,
		PositiveLongTestSuite.class,
		PositiveDurationValidatorTestSuite.class,
		ValidDateFormatValidatorTestSuite.class,
		URIWithIDTestSuite.class
})
public class ConstraintPackageTestSuite {
}