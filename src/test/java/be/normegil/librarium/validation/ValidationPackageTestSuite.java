package be.normegil.librarium.validation;

import be.normegil.librarium.validation.constraint.ConstraintPackageTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		ConstraintPackageTestSuite.class
})
public class ValidationPackageTestSuite {
}