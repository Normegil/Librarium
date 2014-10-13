package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTMovie {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Movie> FACTORY = FactoryRepository.get(Movie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<MovieSerie> MOVIE_SERIE_FACTORY = FactoryRepository.get(MovieSerie.class);
	private Movie entity;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getDefault();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testCopyConstructor() throws Exception {
		Movie copy = new Movie(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testSetSerie() throws Exception {
		MovieSerie serie = MOVIE_SERIE_FACTORY.getNew();
		entity.setSerie(serie);
		assertEquals(serie, entity.getSerie());
	}
}
