package be.normegil.librarium.libraries;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTClassSafety.class,
		UTClass.class,
		UTClassComparable.class
})
public class ClassWrapperTestSuite {
}