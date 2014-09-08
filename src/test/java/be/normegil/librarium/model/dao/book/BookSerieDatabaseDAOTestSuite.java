package be.normegil.librarium.model.dao.book;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTBookSerieDatabaseDAOSafety.class,
		UTBookSerieDatabaseDAO.class
})
public class BookSerieDatabaseDAOTestSuite {
}