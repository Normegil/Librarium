package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.BaseMedia;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTBookSerieSafety.class,
		UTBookSerie.class,
		UTBookSerieEquality.class,
		UTBookSerieComparator.class,
		UTBookSerieBuilderSafety.class,
		UTBookSerieBuilder.class,
		UTBookSerieDatabaseSupport.class
})
public class BookSerieTestSuite implements DataFactory<BookSerie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BaseMedia> BASE_MEDIA_FACTORY = FactoryRepository.get(BaseMedia.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Book> BOOK_FACTORY = FactoryRepository.get(Book.class);

	@Override
	public BookSerie getDefault() {
		return getDefault(true, false);
	}

	@Override
	public BookSerie getNew() {
		return getNew(true, false);
	}

	@Override
	public BookSerie getDefault(final boolean withLink, final boolean withIds) {
		BookSerie.Builder builder = BookSerie.builder()
				.from(BASE_MEDIA_FACTORY.getDefault(withLink, withIds));
		if (withLink) {
			builder.addBook(BOOK_FACTORY.getDefault(false, withIds));
		}
		return builder.build();
	}

	@Override
	public BookSerie getNew(final boolean withLink, final boolean withIds) {
		BookSerie.Builder builder = BookSerie.builder()
				.from(BASE_MEDIA_FACTORY.getNew(withLink, withIds));
		if (withLink) {
			builder.addBook(BOOK_FACTORY.getNew(false, withIds));
		}
		return builder.build();
	}
}