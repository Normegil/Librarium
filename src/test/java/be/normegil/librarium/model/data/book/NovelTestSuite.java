package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTNovelSafety.class,
		UTNovel.class,
		UTNovelEquality.class,
		UTNovelComparator.class,
		UTNovelBuilderSafety.class,
		UTNovelBuilder.class,
		UTNovelDatabaseSupport.class
})
public class NovelTestSuite implements DataFactory<Novel> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Book> BOOK_FACTORY = FactoryRepository.get(Book.class);

	@Override
	public Novel getDefault() {
		return getDefault(true);
	}

	@Override
	public Novel getNew() {
		return getNew(true);
	}

	@Override
	public Novel getDefault(boolean withLink) {
		return Novel.builder()
				.from(BOOK_FACTORY.getDefault(withLink))
				.build();
	}

	@Override
	public Novel getNew(boolean withLink) {
		return Novel.builder()
				.from(BOOK_FACTORY.getDefault(withLink))
				.build();
	}
}