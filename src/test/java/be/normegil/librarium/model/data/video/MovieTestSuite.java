package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.UUID;

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
	private static final UUID DEFAULT_ID = UUID.fromString("");

	@Override
	public Movie getDefault() {
		return getDefault(true, false);
	}

	@Override
	public Movie getNew() {
		return getNew(true, false);
	}

	@Override
	public Movie getDefault(final boolean withLink, final boolean withIds) {
		Movie.Builder builder = Movie.builder()
				.from(VIDEO_FACTORY.getDefault(withLink, withIds));
		if (withLink) {
			builder.setSerie(MOVIE_SERIE_FACTORY.getDefault(false, withIds));
		}
		Movie movie = builder.build();
		if (withIds) {
			new EntityHelper().setId(movie, DEFAULT_ID);
		}
		return movie;
	}

	@Override
	public Movie getNew(final boolean withLink, final boolean withIds) {
		Movie.Builder builder = Movie.builder()
				.from(VIDEO_FACTORY.getNew(withLink, withIds));
		if (withLink) {
			builder.setSerie(MOVIE_SERIE_FACTORY.getNew(false, withIds));
		}
		Movie movie = builder.build();
		if (withIds) {
			new EntityHelper().setId(movie, UUID.randomUUID());
		}
		return movie;
	}
}