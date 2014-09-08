package be.normegil.librarium.model.dao.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.video.Movie;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.dao.AbstractDAOTest;

import static org.junit.Assert.assertEquals;

public class UTMovieDatabaseDAO extends AbstractDAOTest<MovieDatabaseDAO, Movie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Movie> FACTORY = FactoryRepository.get(Movie.class);
	private static final String ALTERNATIVE_TITLE = "AlternativeTitle";

	@Override
	protected MovieDatabaseDAO initDAO() {
		return new MovieDatabaseDAO();
	}

	@Override
	protected Object getEntityId(final Movie entity) {
		return entity.getId();
	}

	@Override
	protected void changeEntity(final Movie entity) {
		entity.setTitle(ALTERNATIVE_TITLE);
	}

	@Override
	protected void assertChangedPropertyEquals(final Movie foundEntity) {
		assertEquals(ALTERNATIVE_TITLE, foundEntity.getTitle());
	}

	@Override
	protected Movie getNewData() {
		return FACTORY.getNext();
	}
}
