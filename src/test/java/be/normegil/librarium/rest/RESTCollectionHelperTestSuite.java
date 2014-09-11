package be.normegil.librarium.rest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTRESTCollectionHelperSafety.class,
		UTRESTCollectionHelper.class
})
public class RESTCollectionHelperTestSuite {
}