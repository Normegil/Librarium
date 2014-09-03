package be.normegil.librarium.model.data.book;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		BookSerieTestSuite.class,
		BookTestSuite.class,

		NovelTestSuite.class,

		AbstractBDTestSuite.class,
		BDTestSuite.class,
		ComicTestSuite.class,
		MangaTestSuite.class
})
public class BookPackageTestSuite {
}
