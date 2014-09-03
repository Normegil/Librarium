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
		UTSerieSeasonSafety.class,
		UTSerieSeason.class,
		UTSerieSeasonEquality.class,
		UTSerieSeasonComparator.class,
		UTSerieSeasonBuilderSafety.class,
		UTSerieSeasonBuilder.class,
		UTSerieSeasonDatabaseSupport.class
})
public class SerieSeasonTestSuite implements DataFactory<SerieSeason> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BaseMedia> BASE_MEDIA_FACTORY = FactoryRepository.get(BaseMedia.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Serie> SERIE_FACTORY = FactoryRepository.get(Serie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<EpisodeSerie> EPISODE_SERIE_FACTORY = FactoryRepository.get(EpisodeSerie.class);
	private Long index = 0L;

	@Override
	public SerieSeason getNew() {
		return getNew(true);
	}

	@Override
	public SerieSeason getNext() {
		return getNext(true);
	}

	@Override
	public SerieSeason getNew(boolean withLink) {
		SerieSeason.Builder builder = SerieSeason.builder()
				.from(BASE_MEDIA_FACTORY.getNew(withLink))
				.setSeasonNumber(1L);

		if (withLink) {
			builder.setSerie(SERIE_FACTORY.getNew(false))
					.addEpisode(EPISODE_SERIE_FACTORY.getNew(false));
		}

		return builder.build();

	}

	@Override
	public SerieSeason getNext(boolean withLink) {
		SerieSeason.Builder builder = SerieSeason.builder()
				.from(BASE_MEDIA_FACTORY.getNew(withLink))
				.setSeasonNumber(index);

		if (withLink) {
			builder.setSerie(SERIE_FACTORY.getNext())
					.addEpisode(EPISODE_SERIE_FACTORY.getNext())
					.addEpisode(EPISODE_SERIE_FACTORY.getNext());
		}

		index += 1;
		return builder.build();
	}
}