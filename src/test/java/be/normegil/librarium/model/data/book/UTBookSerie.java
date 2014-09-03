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

public class UTBookSerie {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BookSerie> FACTORY = FactoryRepository.get(BookSerie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Book> BOOK_FACTORY = FactoryRepository.get(Book.class);
	private BookSerie entity;
	private Collection<Book> books;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getNew();
		books = entity.getBooks();
	}

	@After
	public void tearDown() throws Exception {
		books = null;
		entity = null;
	}

	@Test
	public void testCopyConstructor() throws Exception {
		BookSerie copy = new BookSerie(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testAddAllBooks() throws Exception {
		Collection<Book> toAdd = new HashSet<>();
		toAdd.add(BOOK_FACTORY.getNext());
		toAdd.add(BOOK_FACTORY.getNext());
		toAdd.add(BOOK_FACTORY.getNext());

		books.addAll(toAdd);
		entity.addAllBooks(toAdd);
		assertEquals(books, entity.getBooks());
	}

	@Test
	public void testAddBook() throws Exception {
		Book toAdd = BOOK_FACTORY.getNext();
		books.add(toAdd);
		entity.addBook(toAdd);
		assertEquals(books, entity.getBooks());
	}

	@Test
	public void testRemoveAllBooks() throws Exception {
		Book base = BOOK_FACTORY.getNext();
		Book second = BOOK_FACTORY.getNext();
		Book third = BOOK_FACTORY.getNext();

		Collection<Book> toAdd = new HashSet<>();
		toAdd.add(base);
		toAdd.add(second);
		toAdd.add(third);

		Collection<Book> toRemove = new HashSet<>();
		toRemove.add(second);
		toRemove.add(third);

		books.addAll(toAdd);
		entity.addAllBooks(toAdd);

		books.removeAll(toRemove);
		entity.removeAllBooks(toRemove);

		assertEquals(books, entity.getBooks());
	}

	@Test
	public void testRemoveBook() throws Exception {
		Book toRemove = entity.getBooks().iterator().next();
		books.remove(toRemove);
		entity.removeBook(toRemove);

		assertEquals(books, entity.getBooks());
	}
}
