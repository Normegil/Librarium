package be.normegil.librarium.libraries;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTURLSafety.class,
		UTURL.class,
		UTURLComparable.class,
		UTURLComparator.class,
		UTUrlConverter.class
})
public class URLTestSuite {
}