package be.normegil.librarium.model.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UTGameDatabaseDAO_Safety.class,
        UTGameDatabaseDAO.class
})
public class GameDatabaseDAOTestSuite {
}
