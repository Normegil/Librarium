package be.normegil.librarium.model.dao.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.video.EpisodeSerie;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.dao.AbstractDAOTest;

import static org.junit.Assert.assertEquals;

public class UTEpisodeSerieDatabaseDAO extends AbstractDAOTest<EpisodeSerieDatabaseDAO, EpisodeSerie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<EpisodeSerie> FACTORY = FactoryRepository.get(EpisodeSerie.class);
	private static final String ALTERNATIVE_TITLE = "AlternativeTitle";

	@Override
	protected EpisodeSerieDatabaseDAO initDAO() {
		return new EpisodeSerieDatabaseDAO();
	}

	@Override
	protected Object getEntityId(final EpisodeSerie entity) {
		return entity.getId();
	}

	@Override
	protected void changeEntity(final EpisodeSerie entity) {
		entity.setTitle(ALTERNATIVE_TITLE);
	}

	@Override
	protected void assertChangedPropertyEquals(final EpisodeSerie foundEntity) {
		assertEquals(ALTERNATIVE_TITLE, foundEntity.getTitle());
	}

	@Override
	protected EpisodeSerie getNewData() {
		return FACTORY.getNext();
	}
}
