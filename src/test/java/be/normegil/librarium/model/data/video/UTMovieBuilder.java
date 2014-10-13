package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTMovieBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Movie> MOVIE_FACTORY = FactoryRepository.get(Movie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<MovieSerie> MOVIE_SERIE_FACTORY = FactoryRepository.get(MovieSerie.class);
	private Movie.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = Movie.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFrom() throws Exception {
		Movie movie = MOVIE_FACTORY.getNew();
		Movie copy = entity.from(movie).build();
		assertEquals(movie, copy);
	}

	@Test
	public void testSetSerie() throws Exception {
		MovieSerie serie = MOVIE_SERIE_FACTORY.getNew();
		Movie movie = entity
				.setSerie(serie)
				.build();
		assertEquals(serie, movie.getSerie());
	}
}