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
		UTEpisodeSerieSafety.class,
		UTEpisodeSerie.class,
		UTEpisodeSerieEquality.class,
		UTEpisodeSerieComparator.class,
		UTEpisodeSerieBuilderSafety.class,
		UTEpisodeSerieBuilder.class,
		UTEpisodeSerieDatabaseSupport.class
})
public class EpisodeSerieTestSuite implements DataFactory<EpisodeSerie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Video> VIDEO_FACTORY = FactoryRepository.get(Video.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<SerieSeason> SERIE_SEASON_FACTORY = FactoryRepository.get(SerieSeason.class);
	public static final long DEFAULT_EPISODE_NUMBER = 1L;
	public static final UUID DEFAULT_ID = UUID.fromString("12585b63-d83d-4f38-a4bb-6b718bf7e1f6");
	private static long index = 0L;

	@Override
	public EpisodeSerie getDefault() {
		return getDefault(true, false);
	}

	@Override
	public EpisodeSerie getNew() {
		return getNew(true, false);
	}

	@Override
	public EpisodeSerie getDefault(final boolean withLink, final boolean withIds) {
		EpisodeSerie.Builder builder = EpisodeSerie.builder()
				.from(VIDEO_FACTORY.getDefault(withLink, withIds))
				.setEpisodeNumber(DEFAULT_EPISODE_NUMBER);

		if (withLink) {
			builder.setSeason(SERIE_SEASON_FACTORY.getDefault(false, withIds));
		}

		EpisodeSerie episodeSerie = builder.build();
		if(withIds){
			      new EntityHelper().setId(episodeSerie, DEFAULT_ID);
		}
		return episodeSerie;
	}

	@Override
	public EpisodeSerie getNew(final boolean withLink, final boolean withIds) {
		EpisodeSerie.Builder builder = EpisodeSerie.builder()
				.from(VIDEO_FACTORY.getNew(withLink, withIds))
				.setEpisodeNumber(index);

		if (withLink) {
			builder.setSeason(SERIE_SEASON_FACTORY.getNew(false, withIds));
		}

		index += 1;
		EpisodeSerie episodeSerie = builder.build();
		if(withIds){
			new EntityHelper().setId(episodeSerie, UUID.randomUUID());
		}
		return episodeSerie;
	}
}