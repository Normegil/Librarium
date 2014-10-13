package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.Media;
import be.normegil.librarium.model.data.fake.FakeBook;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.UUID;

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

	public static final UUID DEFAULT_ID = UUID.fromString("");
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> MEDIA_FACTORY = FactoryRepository.get(Media.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BookSerie> BOOK_SERIE_FACTORY = FactoryRepository.get(BookSerie.class);

	@Override
	public Book getDefault() {
		return getDefault(true, false);
	}

	@Override
	public Book getNew() {
		return getNew(true, false);
	}

	@Override
	public Book getDefault(final boolean withLink, final boolean withIds) {
		FakeBook.Builder builder = FakeBook.builder()
				.from(MEDIA_FACTORY.getDefault(withLink, withIds));

		if (withLink) {
			builder.addAuthor(RESPONSIBLE_FACTORY.getDefault(false, withIds))
					.addEditor(RESPONSIBLE_FACTORY.getDefault(false, withIds))
					.setSerie(BOOK_SERIE_FACTORY.getDefault(false, withIds));
		}

		Book book = builder.build();
		if (withIds) {
			new EntityHelper().setId(book, DEFAULT_ID);
		}
		return book;
	}

	@Override
	public Book getNew(final boolean withLink, final boolean withIds) {
		FakeBook.Builder builder = FakeBook.builder()
				.from(MEDIA_FACTORY.getNew(withLink, withIds));

		if (withLink) {
			builder.addAuthor(RESPONSIBLE_FACTORY.getNew(false, withIds))
					.addAuthor(RESPONSIBLE_FACTORY.getNew(false, withIds))
					.addEditor(RESPONSIBLE_FACTORY.getNew(false, withIds))
					.addEditor(RESPONSIBLE_FACTORY.getNew(false, withIds))
					.setSerie(BOOK_SERIE_FACTORY.getNew(false, withIds));
		}

		Book book = builder.build();
		if (withIds) {
			new EntityHelper().setId(book, UUID.randomUUID());
		}
		return book;
	}
}