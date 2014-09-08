package be.normegil.librarium.model.dao;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.Support;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.dao.AbstractDAOTest;

import static org.junit.Assert.assertEquals;

public class UTSupportDatabaseDAO extends AbstractDAOTest<SupportDatabaseDAO, Support> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Support> FACTORY = FactoryRepository.get(Support.class);
	private static final String ALTERNATIVE_TITLE = "AlternativeTitle";

	@Override
	protected SupportDatabaseDAO initDAO() {
		return new SupportDatabaseDAO();
	}

	@Override
	protected Object getEntityId(final Support entity) {
		return entity.getId();
	}

	@Override
	protected void changeEntity(final Support entity) {
		entity.setName(ALTERNATIVE_TITLE);
	}

	@Override
	protected void assertChangedPropertyEquals(final Support foundEntity) {
		assertEquals(ALTERNATIVE_TITLE, foundEntity.getName());
	}

	@Override
	protected Support getNewData() {
		return FACTORY.getNext();
	}
}
