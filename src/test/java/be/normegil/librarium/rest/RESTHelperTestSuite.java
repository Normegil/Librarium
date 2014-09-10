package be.normegil.librarium.rest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTRESTHelperSafety.class,
		UTRESTHelperGetAll.class,
		UTRESTHelper.class
})
public class RESTHelperTestSuite {
}