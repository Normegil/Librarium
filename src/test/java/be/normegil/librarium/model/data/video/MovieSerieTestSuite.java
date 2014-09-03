package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.BaseMedia;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.TestResult;
import be.normegil.librarium.tool.comparator.PropertyComparatorHelper;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTMovieSerieSafety.class,
		UTMovieSerie.class,
		UTMovieSerieEquality.class,
		UTMovieSerieComparator.class,
		UTMovieSerieBuilderSafety.class,
		UTMovieSerieBuilder.class,
		UTMovieSerieDatabaseSupport.class
})
public class MovieSerieTestSuite implements DataFactory<MovieSerie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BaseMedia> BASE_MEDIA_FACTORY = FactoryRepository.get(BaseMedia.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Movie> MOVIE_FACTORY = FactoryRepository.get(Movie.class);

	@Override
	public MovieSerie getNew() {
		return getNew(true);
	}

	@Override
	public MovieSerie getNext() {
		return getNext(true);
	}

	@Override
	public MovieSerie getNew(boolean withLink) {
		MovieSerie.Builder builder = MovieSerie.builder()
				.from(BASE_MEDIA_FACTORY.getNew(withLink));
		if (withLink) {
			builder.addMovie(MOVIE_FACTORY.getNew(false));
		}
		return builder.build();
	}

	@Override
	public MovieSerie getNext(boolean withLink) {
		MovieSerie.Builder builder = MovieSerie.builder()
				.from(BASE_MEDIA_FACTORY.getNext(withLink));
		if (withLink) {
			builder.addMovie(MOVIE_FACTORY.getNext(false));
		}
		return builder.build();
	}
}