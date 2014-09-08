package be.normegil.librarium.model.dao.game;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTGameDatabaseDAOSafety.class,
		UTGameDatabaseDAO.class
})
public class GameDatabaseDAOTestSuite {
}