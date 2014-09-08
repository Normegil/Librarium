package be.normegil.librarium.model.dao;

import be.normegil.librarium.model.dao.book.BookPackageTestSuite;
import be.normegil.librarium.model.dao.game.GamePackageTestSuite;
import be.normegil.librarium.model.dao.people.PeoplePackageTestSuite;
import be.normegil.librarium.model.dao.video.VideoPackageTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		SupportDatabaseDAOTestSuite.class,
		UniverseDatabaseDAOTestSuite.class,

		BookPackageTestSuite.class,
		GamePackageTestSuite.class,
		PeoplePackageTestSuite.class,
		VideoPackageTestSuite.class
})
public class DAOPackageTestSuite {
}
