package be.normegil.librarium.model.dao.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.video.SerieSeason;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.dao.AbstractDAOTest;

import static org.junit.Assert.assertEquals;

public class UTSerieSeasonDatabaseDAO extends AbstractDAOTest<SerieSeasonDatabaseDAO, SerieSeason> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<SerieSeason> FACTORY = FactoryRepository.get(SerieSeason.class);
	private static final String ALTERNATIVE_TITLE = "AlternativeTitle";

	@Override
	protected SerieSeasonDatabaseDAO initDAO() {
		return new SerieSeasonDatabaseDAO();
	}

	@Override
	protected Object getEntityId(final SerieSeason entity) {
		return entity.getId();
	}

	@Override
	protected void changeEntity(final SerieSeason entity) {
		entity.setTitle(ALTERNATIVE_TITLE);
	}

	@Override
	protected void assertChangedPropertyEquals(final SerieSeason foundEntity) {
		assertEquals(ALTERNATIVE_TITLE, foundEntity.getTitle());
	}

	@Override
	protected SerieSeason getNewData() {
		return FACTORY.getNext();
	}
}
