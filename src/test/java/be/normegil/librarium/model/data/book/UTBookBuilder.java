package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.fake.FakeBook;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTBookBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Book> BOOK_FACTORY = FactoryRepository.get(Book.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BookSerie> BOOK_SERIE_FACTORY = FactoryRepository.get(BookSerie.class);
	private FakeBook.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = FakeBook.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFrom() throws Exception {
		Book book = BOOK_FACTORY.getNext();
		Book copy = entity.from(book).build();
		assertEquals(book, copy);
	}

	@Test
	public void testAddAllAuthors() throws Exception {
		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());

		Book book = entity
				.addAllAuthors(toAdd)
				.build();

		assertTrue(book.getAuthors().containsAll(toAdd));
	}

	@Test
	public void testAddAuthor() throws Exception {
		Responsible responsible = RESPONSIBLE_FACTORY.getNext();
		Book book = entity
				.addAuthor(responsible)
				.build();

		assertTrue(book.getAuthors().contains(responsible));
	}

	@Test
	public void testAddAllEditors() throws Exception {
		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());

		Book book = entity
				.addAllEditors(toAdd)
				.build();

		assertTrue(book.getEditors().containsAll(toAdd));
	}

	@Test
	public void testAddEditor() throws Exception {
		Responsible responsible = RESPONSIBLE_FACTORY.getNext();
		Book book = entity
				.addEditor(responsible)
				.build();

		assertTrue(book.getEditors().contains(responsible));
	}
}