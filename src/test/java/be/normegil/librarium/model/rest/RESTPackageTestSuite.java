package be.normegil.librarium.model.rest;

import be.normegil.librarium.model.rest.exception.ExceptionPackageTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		CollectionResourceTestSuite.class,

		ExceptionPackageTestSuite.class
})
public class RESTPackageTestSuite {
}