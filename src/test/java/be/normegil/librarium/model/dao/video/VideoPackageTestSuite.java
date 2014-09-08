package be.normegil.librarium.model.dao.video;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		EpisodeSerieDatabaseDAOTestSuite.class,
		MovieDatabaseDAOTestSuite.class,
		MovieSerieDatabaseDAOTestSuite.class,
		SerieDatabaseDAOTestSuite.class,
		SerieSeasonDatabaseDAOTestSuite.class
})
public class VideoPackageTestSuite {
}