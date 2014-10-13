package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.BaseMedia;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.UUID;

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
	private static final UUID DEFAULT_ID = UUID.fromString("4965d248-b3f0-4435-80d8-0346b1863d08");

	@Override
	public MovieSerie getDefault() {
		return getDefault(true, false);
	}

	@Override
	public MovieSerie getNew() {
		return getNew(true, false);
	}

	@Override
	public MovieSerie getDefault(final boolean withLink, final boolean withIds) {
		MovieSerie.Builder builder = MovieSerie.builder()
				.from(BASE_MEDIA_FACTORY.getDefault(withLink, withIds));
		if (withLink) {
			builder.addMovie(MOVIE_FACTORY.getDefault(false, withIds));
		}
		MovieSerie serie = builder.build();
		if (withIds) {
			new EntityHelper().setId(serie, DEFAULT_ID);
		}
		return serie;
	}

	@Override
	public MovieSerie getNew(final boolean withLink, final boolean withIds) {
		MovieSerie.Builder builder = MovieSerie.builder()
				.from(BASE_MEDIA_FACTORY.getNew(withLink, withIds));
		if (withLink) {
			builder.addMovie(MOVIE_FACTORY.getNew(false, withIds));
		}
		MovieSerie serie = builder.build();
		if (withIds) {
			new EntityHelper().setId(serie, DEFAULT_ID);
		}
		return serie;
	}
}