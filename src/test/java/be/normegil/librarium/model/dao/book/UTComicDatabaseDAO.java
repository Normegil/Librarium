package be.normegil.librarium.model.dao.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.book.Comic;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.dao.AbstractDAOTest;

import static org.junit.Assert.assertEquals;

public class UTComicDatabaseDAO extends AbstractDAOTest<ComicDatabaseDAO, Comic> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Comic> FACTORY = FactoryRepository.get(Comic.class);
	private static final String ALTERNATIVE_TITLE = "AlternativeTitle";

	@Override
	protected ComicDatabaseDAO initDAO() {
		return new ComicDatabaseDAO();
	}

	@Override
	protected Object getEntityId(final Comic entity) {
		return entity.getId();
	}

	@Override
	protected void changeEntity(final Comic entity) {
		entity.setTitle(ALTERNATIVE_TITLE);
	}

	@Override
	protected void assertChangedPropertyEquals(final Comic foundEntity) {
		assertEquals(ALTERNATIVE_TITLE, foundEntity.getTitle());
	}

	@Override
	protected Comic getNewData() {
		return FACTORY.getNext();
	}
}
