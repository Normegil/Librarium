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
	public BookSerie getNew() {
		return getNew(true);
	}

	@Override
	public BookSerie getNext() {
		return getNext(true);
	}

	@Override
	public BookSerie getNew(boolean withLink) {
		BookSerie.Builder builder = BookSerie.builder()
				.from(BASE_MEDIA_FACTORY.getNew(withLink));
		if (withLink) {
			builder.addBook(BOOK_FACTORY.getNew(false));
		}
		return builder.build();
	}

	@Override
	public BookSerie getNext(boolean withLink) {
		BookSerie.Builder builder = BookSerie.builder()
				.from(BASE_MEDIA_FACTORY.getNext(withLink));
		if (withLink) {
			builder.addBook(BOOK_FACTORY.getNext(false));
		}
		return builder.build();
	}
}