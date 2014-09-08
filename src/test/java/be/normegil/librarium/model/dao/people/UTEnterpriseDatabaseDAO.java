package be.normegil.librarium.model.dao.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.people.Enterprise;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.dao.AbstractDAOTest;

import static org.junit.Assert.assertEquals;

public class UTEnterpriseDatabaseDAO extends AbstractDAOTest<EnterpriseDatabaseDAO, Enterprise> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Enterprise> FACTORY = FactoryRepository.get(Enterprise.class);
	private static final String ALTERNATIVE_TITLE = "AlternativeTitle";

	@Override
	protected EnterpriseDatabaseDAO initDAO() {
		return new EnterpriseDatabaseDAO();
	}

	@Override
	protected Object getEntityId(final Enterprise entity) {
		return entity.getId();
	}

	@Override
	protected void changeEntity(final Enterprise entity) {
		entity.setName(ALTERNATIVE_TITLE);
	}

	@Override
	protected void assertChangedPropertyEquals(final Enterprise foundEntity) {
		assertEquals(ALTERNATIVE_TITLE, foundEntity.getName());
	}

	@Override
	protected Enterprise getNewData() {
		return FACTORY.getNext();
	}
}
