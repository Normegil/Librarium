package be.normegil.librarium.model.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTDatabaseDAOSafety.class,
		UTDatabaseDAO.class
})
public class DatabaseDAOTestSuite {
}