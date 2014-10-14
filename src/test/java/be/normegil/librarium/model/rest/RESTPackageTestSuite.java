package be.normegil.librarium.model.rest;

import be.normegil.librarium.model.rest.exception.ExceptionPackageTestSuite;
import be.normegil.librarium.model.rest.services.ServicePackageTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		CollectionResourceTestSuite.class,
		RESTHelperTestSuite.class,

		ExceptionPackageTestSuite.class,
		ServicePackageTestSuite.class
})
public class RESTPackageTestSuite {
}