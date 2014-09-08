package be.normegil.librarium.model.dao;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.Universe;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.dao.AbstractDAOTest;

import static org.junit.Assert.assertEquals;

public class UTUniverseDatabaseDAO extends AbstractDAOTest<UniverseDatabaseDAO, Universe> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Universe> FACTORY = FactoryRepository.get(Universe.class);
	private static final String ALTERNATIVE_TITLE = "AlternativeTitle";

	@Override
	protected UniverseDatabaseDAO initDAO() {
		return new UniverseDatabaseDAO();
	}

	@Override
	protected Object getEntityId(final Universe entity) {
		return entity.getId();
	}

	@Override
	protected void changeEntity(final Universe entity) {
		entity.setName(ALTERNATIVE_TITLE);
	}

	@Override
	protected void assertChangedPropertyEquals(final Universe foundEntity) {
		assertEquals(ALTERNATIVE_TITLE, foundEntity.getName());
	}

	@Override
	protected Universe getNewData() {
		return FACTORY.getNext();
	}
}
