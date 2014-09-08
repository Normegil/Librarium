package be.normegil.librarium.model.dao.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.book.BookSerie;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.dao.AbstractDAOTest;

import static org.junit.Assert.assertEquals;

public class UTBookSerieDatabaseDAO extends AbstractDAOTest<BookSerieDatabaseDAO, BookSerie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BookSerie> FACTORY = FactoryRepository.get(BookSerie.class);
	private static final String ALTERNATIVE_TITLE = "AlternativeTitle";

	@Override
	protected BookSerieDatabaseDAO initDAO() {
		return new BookSerieDatabaseDAO();
	}

	@Override
	protected Object getEntityId(final BookSerie entity) {
		return entity.getId();
	}

	@Override
	protected void changeEntity(final BookSerie entity) {
		entity.setTitle(ALTERNATIVE_TITLE);
	}

	@Override
	protected void assertChangedPropertyEquals(final BookSerie foundEntity) {
		assertEquals(ALTERNATIVE_TITLE, foundEntity.getTitle());
	}

	@Override
	protected BookSerie getNewData() {
		return FACTORY.getNext();
	}
}
