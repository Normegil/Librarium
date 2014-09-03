package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.TestResult;
import be.normegil.librarium.tool.comparator.PropertyComparatorHelper;
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
	public Novel getNew() {
		return getNew(true);
	}

	@Override
	public Novel getNext() {
		return getNext(true);
	}

	@Override
	public Novel getNew(boolean withLink) {
		return Novel.builder()
				.from(BOOK_FACTORY.getNew(withLink))
				.build();
	}

	@Override
	public Novel getNext(boolean withLink) {
		return Novel.builder()
				.from(BOOK_FACTORY.getNew(withLink))
				.build();
	}
}