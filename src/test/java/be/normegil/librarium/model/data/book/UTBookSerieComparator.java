package be.normegil.librarium.model.data.book;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.AbstractDataComparableTest;
import be.normegil.librarium.tool.test.AbstractDataComparatorTest;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class UTBookSerieComparator extends AbstractDataComparableTest<BookSerie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BookSerie> FACTORY = FactoryRepository.get(BookSerie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Book> BOOK_FACTORY = FactoryRepository.get(Book.class);

	@Override
	protected BookSerie getNewEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected int compare(final BookSerie entity1, final BookSerie entity2) {
		return entity1.compareTo(entity2);
	}

	@Override
	public void testEquality() throws Exception {
		BookSerie entity = getEntity();
		BookSerie copy = new BookSerie(entity);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
	}

	@Test
	public void testBook_First() throws Exception {
		BookSerie entity = getEntity();
		BookSerie copy = new BookSerie(entity);
		copy.addBook(BOOK_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testBook_Second() throws Exception {
		BookSerie entity = getEntity();
		BookSerie copy = new BookSerie(entity);
		copy.addBook(BOOK_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}
}
