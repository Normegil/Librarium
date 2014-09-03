package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class UTMovieSerie {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<MovieSerie> FACTORY = FactoryRepository.get(MovieSerie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Movie> MOVIE_FACTORY = FactoryRepository.get(Movie.class);
	private MovieSerie entity;
	private Collection<Movie> movies;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getNext();
		movies = entity.getMovies();
	}

	@After
	public void tearDown() throws Exception {
		movies = null;
		entity = null;
	}

	@Test
	public void testCopyConstructor() throws Exception {
		MovieSerie copy = new MovieSerie(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testAddAllMovies() throws Exception {
		Collection<Movie> toAdd = new HashSet<>();
		toAdd.add(MOVIE_FACTORY.getNext());
		toAdd.add(MOVIE_FACTORY.getNext());
		toAdd.add(MOVIE_FACTORY.getNext());

		movies.addAll(toAdd);
		entity.addAllMovies(toAdd);
		assertEquals(movies, entity.getMovies());
	}

	@Test
	public void testAddMovie() throws Exception {
		Movie toAdd = MOVIE_FACTORY.getNext();
		movies.add(toAdd);
		entity.addMovie(toAdd);
		assertEquals(movies, entity.getMovies());
	}

	@Test
	public void testRemoveAllMovies() throws Exception {
		Movie base = MOVIE_FACTORY.getNext();
		Movie second = MOVIE_FACTORY.getNext();
		Movie third = MOVIE_FACTORY.getNext();

		Collection<Movie> toAdd = new HashSet<>();
		toAdd.add(base);
		toAdd.add(second);
		toAdd.add(third);

		Collection<Movie> toRemove = new HashSet<>();
		toRemove.add(second);
		toRemove.add(third);

		movies.addAll(toAdd);
		entity.addAllMovies(toAdd);

		movies.removeAll(toRemove);
		entity.removeAllMovies(toRemove);

		assertEquals(movies, entity.getMovies());
	}

	@Test
	public void testRemoveMovie() throws Exception {
		Movie toRemove = entity.getMovies().iterator().next();
		movies.remove(toRemove);
		entity.removeMovie(toRemove);

		assertEquals(movies, entity.getMovies());
	}
}
