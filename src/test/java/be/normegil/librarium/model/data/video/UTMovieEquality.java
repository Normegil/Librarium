package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataEqualityTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTMovieEquality extends AbstractDataEqualityTest<Movie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Movie> FACTORY = FactoryRepository.get(Movie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<MovieSerie> MOVIE_SERIE_FACTORY = FactoryRepository.get(MovieSerie.class);

	@Override
	protected Movie getNewEntity() {
		return FACTORY.getNew();
	}

	@Test
	public void testUnchanged() throws Exception {
		Movie entity = getEntity();
		Movie copy = new Movie(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentSerie() throws Exception {
		Movie entity = getEntity();
		Movie copy = new Movie(entity);
		entity.setSerie(MOVIE_SERIE_FACTORY.getNext());
		assertNotEquals(entity, copy);
	}
}