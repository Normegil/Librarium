package be.normegil.librarium.model;

import be.normegil.librarium.model.dao.DAOPackageTestSuite;
import be.normegil.librarium.model.data.DataPackageTestSuite;
import be.normegil.librarium.model.rest.RESTPackageTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DataPackageTestSuite.class,
        DAOPackageTestSuite.class,
		RESTPackageTestSuite.class
})
public class ModelTestSuite {
}
