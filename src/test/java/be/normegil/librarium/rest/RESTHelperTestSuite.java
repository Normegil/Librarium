package be.normegil.librarium.rest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTRESTHelperSafety.class,
		UTRESTHelperGetAll.class,
		UTRESTHelperGet.class,
		UTRESTHelperCreate.class,
		UTRESTHelperUpdateByPUT.class,
		UTRESTHelperUpdateByPOST.class,
		UTRESTHelperDelete.class
})
public class RESTHelperTestSuite {
}