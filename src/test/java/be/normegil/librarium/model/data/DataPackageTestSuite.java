package be.normegil.librarium.model.data;

import be.normegil.librarium.model.data.book.BookPackageTestSuite;
import be.normegil.librarium.model.data.game.GamePackageTestSuite;
import be.normegil.librarium.model.data.people.PeoplePackageTestSuite;
import be.normegil.librarium.model.data.video.VideoPackageTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		BaseMediaTestSuite.class,
		DownloadLinkTestSuite.class,
		EntityTestSuite.class,
		MediaTestSuite.class,
		SupportTestSuite.class,
		ReleaseDateTestSuite.class,
		UniverseTestSuite.class,


		BookPackageTestSuite.class,
		GamePackageTestSuite.class,
		PeoplePackageTestSuite.class,
		VideoPackageTestSuite.class
})
public class DataPackageTestSuite {
}
