package be.normegil.librarium.model.dao.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.video.Serie;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.dao.AbstractDAOTest;

import static org.junit.Assert.assertEquals;

public class UTSerieDatabaseDAO extends AbstractDAOTest<SerieDatabaseDAO, Serie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Serie> FACTORY = FactoryRepository.get(Serie.class);
	private static final String ALTERNATIVE_TITLE = "AlternativeTitle";

	@Override
	protected SerieDatabaseDAO initDAO() {
		return new SerieDatabaseDAO();
	}

	@Override
	protected Object getEntityId(final Serie entity) {
		return entity.getId();
	}

	@Override
	protected void changeEntity(final Serie entity) {
		entity.setTitle(ALTERNATIVE_TITLE);
	}

	@Override
	protected void assertChangedPropertyEquals(final Serie foundEntity) {
		assertEquals(ALTERNATIVE_TITLE, foundEntity.getTitle());
	}

	@Override
	protected Serie getNewData() {
		return FACTORY.getNext();
	}
}
