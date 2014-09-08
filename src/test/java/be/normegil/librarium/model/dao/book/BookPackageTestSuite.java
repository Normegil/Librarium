package be.normegil.librarium.model.dao.book;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		BookSerieDatabaseDAOTestSuite.class,

		BDDatabaseDAOTestSuite.class,
		ComicDatabaseDAOTestSuite.class,
		MangaDatabaseDAOTestSuite.class,
		NovelDatabaseDAOTestSuite.class
})
public class BookPackageTestSuite {
}