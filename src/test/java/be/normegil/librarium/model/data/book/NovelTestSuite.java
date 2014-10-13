package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.UUID;

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
	private static final UUID DEFAULT_ID = UUID.fromString("");

	@Override
	public Novel getDefault() {
		return getDefault(true, false);
	}

	@Override
	public Novel getNew() {
		return getNew(true, false);
	}

	@Override
	public Novel getDefault(final boolean withLink, final boolean withIds) {
		Novel novel = Novel.builder()
				.from(BOOK_FACTORY.getDefault(withLink, withIds))
				.build();
		if (withIds) {
			new EntityHelper().setId(novel, DEFAULT_ID);
		}
		return novel;
	}

	@Override
	public Novel getNew(final boolean withLink, final boolean withIds) {
		Novel novel = Novel.builder()
				.from(BOOK_FACTORY.getDefault(withLink, withIds))
				.build();
		if (withIds) {
			new EntityHelper().setId(novel, UUID.randomUUID());
		}
		return novel;
	}
}