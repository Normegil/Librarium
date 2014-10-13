package be.normegil.librarium.model.data.video;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataComparableTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTMovieSerieComparator extends AbstractDataComparableTest<MovieSerie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<MovieSerie> FACTORY = FactoryRepository.get(MovieSerie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Movie> MOVIE_FACTORY = FactoryRepository.get(Movie.class);

	@Override
	protected MovieSerie getNewEntity() {
		return FACTORY.getDefault();
	}

	@Override
	protected int compare(final MovieSerie entity1, final MovieSerie entity2) {
		return entity1.compareTo(entity2);
	}

	@Override
	public void testEquality() throws Exception {
		MovieSerie entity = getEntity();
		MovieSerie copy = new MovieSerie(entity);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
		assertEquals(compare(entity, copy), entity.compareTo(copy));
	}

	@Test
	public void testMovie_First() throws Exception {
		MovieSerie entity = getEntity();
		MovieSerie copy = new MovieSerie(entity);
		copy.addMovie(MOVIE_FACTORY.getNew());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testMovie_Second() throws Exception {
		MovieSerie entity = getEntity();
		MovieSerie copy = new MovieSerie(entity);
		copy.addMovie(MOVIE_FACTORY.getNew());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}
}
