package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.TestResult;
import be.normegil.librarium.tool.comparator.PropertyComparatorHelper;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTMovieSafety.class,
		UTMovie.class,
		UTMovieEquality.class,
		UTMovieComparator.class,
		UTMovieBuilderSafety.class,
		UTMovieBuilder.class,
		UTMovieDatabaseSupport.class
})
public class MovieTestSuite implements DataFactory<Movie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Video> VIDEO_FACTORY = FactoryRepository.get(Video.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<MovieSerie> MOVIE_SERIE_FACTORY = FactoryRepository.get(MovieSerie.class);

	@Override
	public Movie getNew() {
		return getNew(true);
	}

	@Override
	public Movie getNext() {
		return getNext(true);
	}

	@Override
	public Movie getNew(boolean withLink) {
		Movie.Builder builder = Movie.builder()
				.from(VIDEO_FACTORY.getNew(withLink));
		if (withLink) {
			builder.setSerie(MOVIE_SERIE_FACTORY.getNew(false));
		}
		return builder
				.build();
	}

	@Override
	public Movie getNext(boolean withLink) {
		Movie.Builder builder = Movie.builder()
				.from(VIDEO_FACTORY.getNext(withLink));
		if (withLink) {
			builder.setSerie(MOVIE_SERIE_FACTORY.getNext(false));
		}
		return builder.build();
	}
}