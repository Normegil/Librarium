package be.normegil.librarium.validation.constraint;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		NotEmptyValidatorTestSuite.class,
		ValidDateFormatValidatorTestSuite.class,
		PositiveDurationValidatorTestSuite.class,
		HttpUrlValidatorTestSuite.class
})
public class ConstraintPackageTestSuite {
}