package be.normegil.librarium.model;

import be.normegil.librarium.model.data.DataPackageTestSuite;
import be.normegil.librarium.model.dao.DAOTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DataPackageTestSuite.class,
        DAOTestSuite.class
})
public class ModelTestSuite {
}
