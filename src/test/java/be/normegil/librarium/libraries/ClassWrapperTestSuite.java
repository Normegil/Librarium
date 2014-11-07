package be.normegil.librarium.libraries;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTClassWrapperSafety.class,
		UTClassWrapper.class,
		UTClassWrapperComparable.class
})
public class ClassWrapperTestSuite {
}