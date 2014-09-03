package be.normegil.librarium.model.data.book;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.fake.FakeBook;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.AbstractDataComparableTest;
import be.normegil.librarium.tool.test.AbstractDataComparatorTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTBookComparator extends AbstractDataComparableTest<Book> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Book> FACTORY = FactoryRepository.get(Book.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BookSerie> BOOK_SERIE_FACTORY = FactoryRepository.get(BookSerie.class);

	@Override
	protected Book getNewEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected int compare(final Book entity1, final Book entity2) {
		return entity1.compareTo(entity2);
	}

	@Override
	public void testEquality() throws Exception {
		Book entity = getEntity();
		Book copy = new FakeBook(entity);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
		assertEquals(compare(entity, copy), entity.compareTo(copy));
	}

	@Test
	public void testAuthor_First() throws Exception {
		Book entity = getEntity();
		Book copy = new FakeBook(entity);
		copy.addAuthor(RESPONSIBLE_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testAuthor_Second() throws Exception {
		Book entity = getEntity();
		Book copy = new FakeBook(entity);
		copy.addAuthor(RESPONSIBLE_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}

	@Test
	public void testEditor_First() throws Exception {
		Book entity = getEntity();
		Book copy = new FakeBook(entity);
		copy.addEditor(RESPONSIBLE_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testEditor_Second() throws Exception {
		Book entity = getEntity();
		Book copy = new FakeBook(entity);
		copy.addEditor(RESPONSIBLE_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}

	@Test
	public void testSerie_First() throws Exception {
		Book entity = getEntity();
		Book copy = new FakeBook(entity);
		copy.setSerie(BOOK_SERIE_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testSerie_Second() throws Exception {
		Book entity = getEntity();
		Book copy = new FakeBook(entity);
		copy.setSerie(BOOK_SERIE_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}
}
