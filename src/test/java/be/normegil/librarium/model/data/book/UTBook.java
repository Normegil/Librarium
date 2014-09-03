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

public class UTBook {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Book> FACTORY = FactoryRepository.get(Book.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BookSerie> BOOK_SERIE_FACTORY = FactoryRepository.get(BookSerie.class);
	private Book entity;
	private Collection<Responsible> authors;
	private Collection<Responsible> editors;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getNew();
		authors = entity.getAuthors();
		editors = entity.getEditors();
	}

	@After
	public void tearDown() throws Exception {
		editors = null;
		authors = null;
		entity = null;
	}

	@Test
	public void testCopyConstructor() throws Exception {
		Book copy = new FakeBook(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testAddAllAuthors() throws Exception {
		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());

		authors.addAll(toAdd);
		entity.addAllAuthors(toAdd);
		assertEquals(authors, entity.getAuthors());
	}

	@Test
	public void testAddAuthor() throws Exception {
		Responsible toAdd = RESPONSIBLE_FACTORY.getNext();
		authors.add(toAdd);
		entity.addAuthor(toAdd);
		assertEquals(authors, entity.getAuthors());
	}

	@Test
	public void testRemoveAllAuthors() throws Exception {
		Responsible base = RESPONSIBLE_FACTORY.getNext();
		Responsible second = RESPONSIBLE_FACTORY.getNext();
		Responsible third = RESPONSIBLE_FACTORY.getNext();

		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(base);
		toAdd.add(second);
		toAdd.add(third);

		Collection<Responsible> toRemove = new HashSet<>();
		toRemove.add(second);
		toRemove.add(third);

		authors.addAll(toAdd);
		entity.addAllAuthors(toAdd);

		authors.removeAll(toRemove);
		entity.removeAllAuthors(toRemove);

		assertEquals(authors, entity.getAuthors());
	}

	@Test
	public void testRemoveAuthor() throws Exception {
		Responsible toRemove = entity.getAuthors().iterator().next();
		authors.remove(toRemove);
		entity.removeAuthor(toRemove);

		assertEquals(authors, entity.getAuthors());
	}

	@Test
	public void testAddAllEditors() throws Exception {
		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());

		editors.addAll(toAdd);
		entity.addAllEditors(toAdd);
		assertEquals(editors, entity.getEditors());
	}

	@Test
	public void testAddEditor() throws Exception {
		Responsible toAdd = RESPONSIBLE_FACTORY.getNext();
		editors.add(toAdd);
		entity.addEditor(toAdd);
		assertEquals(editors, entity.getEditors());
	}

	@Test
	public void testRemoveAllEditors() throws Exception {
		Responsible base = RESPONSIBLE_FACTORY.getNext();
		Responsible second = RESPONSIBLE_FACTORY.getNext();
		Responsible third = RESPONSIBLE_FACTORY.getNext();

		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(base);
		toAdd.add(second);
		toAdd.add(third);

		Collection<Responsible> toRemove = new HashSet<>();
		toRemove.add(second);
		toRemove.add(third);

		editors.addAll(toAdd);
		entity.addAllEditors(toAdd);

		editors.removeAll(toRemove);
		entity.removeAllEditors(toRemove);

		assertEquals(editors, entity.getEditors());
	}

	@Test
	public void testRemoveEditor() throws Exception {
		Responsible toRemove = entity.getEditors().iterator().next();
		editors.remove(toRemove);
		entity.removeEditor(toRemove);

		assertEquals(editors, entity.getEditors());
	}

	@Test
	public void testSetSerie() throws Exception {
		BookSerie serie = BOOK_SERIE_FACTORY.getNext();
		entity.setSerie(serie);
		assertEquals(serie, entity.getSerie());
	}
}
