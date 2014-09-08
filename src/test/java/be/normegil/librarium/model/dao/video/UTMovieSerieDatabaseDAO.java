package be.normegil.librarium.model.dao.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.video.MovieSerie;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.dao.AbstractDAOTest;

import static org.junit.Assert.assertEquals;

public class UTMovieSerieDatabaseDAO extends AbstractDAOTest<MovieSerieDatabaseDAO, MovieSerie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<MovieSerie> FACTORY = FactoryRepository.get(MovieSerie.class);
	private static final String ALTERNATIVE_TITLE = "AlternativeTitle";

	@Override
	protected MovieSerieDatabaseDAO initDAO() {
		return new MovieSerieDatabaseDAO();
	}

	@Override
	protected Object getEntityId(final MovieSerie entity) {
		return entity.getId();
	}

	@Override
	protected void changeEntity(final MovieSerie entity) {
		entity.setTitle(ALTERNATIVE_TITLE);
	}

	@Override
	protected void assertChangedPropertyEquals(final MovieSerie foundEntity) {
		assertEquals(ALTERNATIVE_TITLE, foundEntity.getTitle());
	}

	@Override
	protected MovieSerie getNewData() {
		return FACTORY.getNext();
	}
}
