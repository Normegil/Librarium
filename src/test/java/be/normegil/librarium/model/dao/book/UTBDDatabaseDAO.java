package be.normegil.librarium.model.dao.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.book.BD;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.dao.AbstractDAOTest;

import static org.junit.Assert.assertEquals;

public class UTBDDatabaseDAO extends AbstractDAOTest<BDDatabaseDAO, BD> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BD> FACTORY = FactoryRepository.get(BD.class);
	private static final String ALTERNATIVE_TITLE = "AlternativeTitle";

	@Override
	protected BDDatabaseDAO initDAO() {
		return new BDDatabaseDAO();
	}

	@Override
	protected Object getEntityId(final BD entity) {
		return entity.getId();
	}

	@Override
	protected void changeEntity(final BD entity) {
		entity.setTitle(ALTERNATIVE_TITLE);
	}

	@Override
	protected void assertChangedPropertyEquals(final BD foundEntity) {
		assertEquals(ALTERNATIVE_TITLE, foundEntity.getTitle());
	}

	@Override
	protected BD getNewData() {
		return FACTORY.getNext();
	}
}
