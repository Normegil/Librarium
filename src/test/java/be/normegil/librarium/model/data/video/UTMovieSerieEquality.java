package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataEqualityTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTMovieSerieEquality extends AbstractDataEqualityTest<MovieSerie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<MovieSerie> FACTORY = FactoryRepository.get(MovieSerie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Movie> MOVIE_FACTORY = FactoryRepository.get(Movie.class);

	@Override
	protected MovieSerie getNewEntity() {
		return FACTORY.getNew();
	}

	@Test
	public void testUnchanged() throws Exception {
		MovieSerie entity = getEntity();
		MovieSerie copy = new MovieSerie(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentMovie() throws Exception {
		MovieSerie entity = getEntity();
		MovieSerie copy = new MovieSerie(entity);
		entity.addMovie(MOVIE_FACTORY.getNext());
		assertNotEquals(entity, copy);
	}
}