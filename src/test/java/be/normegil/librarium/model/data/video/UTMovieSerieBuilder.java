package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTMovieSerieBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<MovieSerie> MOVIE_SERIE_FACTORY = FactoryRepository.get(MovieSerie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Movie> MOVIE_FACTORY = FactoryRepository.get(Movie.class);
	private MovieSerie.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = MovieSerie.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFrom() throws Exception {
		MovieSerie movieSerie = MOVIE_SERIE_FACTORY.getNext();
		MovieSerie copy = entity.from(movieSerie).build();
		assertEquals(movieSerie, copy);
	}

	@Test
	public void testAddAllMovies() throws Exception {
		Collection<Movie> toAdd = new HashSet<>();
		toAdd.add(MOVIE_FACTORY.getNext());
		toAdd.add(MOVIE_FACTORY.getNext());
		toAdd.add(MOVIE_FACTORY.getNext());

		MovieSerie movieSerie = entity
				.addAllMovies(toAdd)
				.build();

		assertTrue(movieSerie.getMovies().containsAll(toAdd));
	}

	@Test
	public void testAddMovie() throws Exception {
		Movie movie = MOVIE_FACTORY.getNext();
		MovieSerie movieSerie = entity
				.addMovie(movie)
				.build();

		assertTrue(movieSerie.getMovies().contains(movie));
	}
}