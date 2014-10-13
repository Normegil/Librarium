package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTBookSerieBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BookSerie> BOOK_SERIE_FACTORY = FactoryRepository.get(BookSerie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Book> BOOK_FACTORY = FactoryRepository.get(Book.class);
	private BookSerie.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = BookSerie.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFrom() throws Exception {
		BookSerie bookSerie = BOOK_SERIE_FACTORY.getNew();
		BookSerie copy = entity.from(bookSerie).build();
		assertEquals(bookSerie, copy);
	}

	@Test
	public void testAddAllBooks() throws Exception {
		Collection<Book> toAdd = new HashSet<>();
		toAdd.add(BOOK_FACTORY.getNew());
		toAdd.add(BOOK_FACTORY.getNew());
		toAdd.add(BOOK_FACTORY.getNew());

		BookSerie bookSerie = entity
				.addAllBooks(toAdd)
				.build();

		assertTrue(bookSerie.getBooks().containsAll(toAdd));
	}

	@Test
	public void testAddBook() throws Exception {
		Book book = BOOK_FACTORY.getNew();
		BookSerie bookSerie = entity
				.addBook(book)
				.build();

		assertTrue(bookSerie.getBooks().contains(book));
	}
}