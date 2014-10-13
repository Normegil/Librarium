package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataEqualityTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTBookSerieEquality extends AbstractDataEqualityTest<BookSerie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BookSerie> FACTORY = FactoryRepository.get(BookSerie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Book> BOOK_FACTORY = FactoryRepository.get(Book.class);

	@Override
	protected BookSerie getNewEntity() {
		return FACTORY.getDefault();
	}

	@Test
	public void testUnchanged() throws Exception {
		BookSerie entity = getEntity();
		BookSerie copy = new BookSerie(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentBook() throws Exception {
		BookSerie entity = getEntity();
		BookSerie copy = new BookSerie(entity);
		entity.addBook(BOOK_FACTORY.getNew());
		assertNotEquals(entity, copy);
	}
}