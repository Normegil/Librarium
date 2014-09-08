package be.normegil.librarium.model.dao.book;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTNovelDatabaseDAOSafety.class,
		UTNovelDatabaseDAO.class
})
public class NovelDatabaseDAOTestSuite {
}