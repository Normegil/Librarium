package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

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
	private static long index = 0L;

	@Override
	public EpisodeSerie getNew() {
		return getNew(true);
	}

	@Override
	public EpisodeSerie getNext() {
		return getNext(true);
	}

	@Override
	public EpisodeSerie getNew(boolean withLink) {
		EpisodeSerie.Builder builder = EpisodeSerie.builder()
				.from(VIDEO_FACTORY.getNew(withLink))
				.setEpisodeNumber(1L);

		if (withLink) {
			builder.setSeason(SERIE_SEASON_FACTORY.getNew(false));
		}

		return builder.build();
	}

	@Override
	public EpisodeSerie getNext(boolean withLink) {
		EpisodeSerie.Builder builder = EpisodeSerie.builder()
				.from(VIDEO_FACTORY.getNext(withLink))
				.setEpisodeNumber(index);

		if (withLink) {
			builder.setSeason(SERIE_SEASON_FACTORY.getNext(false));
		}

		index += 1;
		return builder.build();
	}
}