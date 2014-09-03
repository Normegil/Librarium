package be.normegil.librarium.model.data.video;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		VideoTestSuite.class,

		MovieSerieTestSuite.class,
		MovieTestSuite.class,

		SerieTestSuite.class,
		SerieSeasonTestSuite.class,
		EpisodeSerieTestSuite.class
})
public class VideoPackageTestSuite {
}