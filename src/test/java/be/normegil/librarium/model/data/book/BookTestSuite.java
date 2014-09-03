package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.Media;
import be.normegil.librarium.model.data.fake.FakeBook;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTBookSafety.class,
		UTBook.class,
		UTBookEquality.class,
		UTBookComparator.class,
		UTBookBuilderSafety.class,
		UTBookBuilder.class,
})
public class BookTestSuite implements DataFactory<Book> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> MEDIA_FACTORY = FactoryRepository.get(Media.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BookSerie> BOOK_SERIE_FACTORY = FactoryRepository.get(BookSerie.class);

	@Override
	public Book getNew() {
		return getNew(true);
	}

	@Override
	public Book getNext() {
		return getNext(true);
	}

	@Override
	public Book getNew(boolean withLink) {
		FakeBook.Builder builder = FakeBook.builder()
				.from(MEDIA_FACTORY.getNew(withLink));

		if (withLink) {
			builder.addAuthor(RESPONSIBLE_FACTORY.getNew(false))
					.addEditor(RESPONSIBLE_FACTORY.getNew(false))
					.setSerie(BOOK_SERIE_FACTORY.getNew(false));
		}

		return builder.build();
	}

	@Override
	public Book getNext(boolean withLink) {
		FakeBook.Builder builder = FakeBook.builder()
				.from(MEDIA_FACTORY.getNext(withLink));

		if (withLink) {
			builder.addAuthor(RESPONSIBLE_FACTORY.getNext(false))
					.addAuthor(RESPONSIBLE_FACTORY.getNext(false))
					.addEditor(RESPONSIBLE_FACTORY.getNext(false))
					.addEditor(RESPONSIBLE_FACTORY.getNext(false))
					.setSerie(BOOK_SERIE_FACTORY.getNext(false));
		}

		return builder.build();
	}
}