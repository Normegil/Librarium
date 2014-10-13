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
	private static final UUID DEFAULT_ID = UUID.fromString("0145cb2b-add2-4843-bab0-589b30a7e079");
	private Long index = 0L;

	@Override
	public SerieSeason getDefault() {
		return getDefault(true, false);
	}

	@Override
	public SerieSeason getNew() {
		return getNew(true, false);
	}

	@Override
	public SerieSeason getDefault(final boolean withLink, final boolean withIds) {
		SerieSeason.Builder builder = SerieSeason.builder()
				.from(BASE_MEDIA_FACTORY.getDefault(withLink, withIds))
				.setSeasonNumber(1L);

		if (withLink) {
			builder.setSerie(SERIE_FACTORY.getDefault(false, withIds))
					.addEpisode(EPISODE_SERIE_FACTORY.getDefault(false, withIds));
		}

		SerieSeason season = builder.build();
		if (withIds) {
			new EntityHelper().setId(season, DEFAULT_ID);
		}
		return season;

	}

	@Override
	public SerieSeason getNew(final boolean withLink, final boolean withIds) {
		SerieSeason.Builder builder = SerieSeason.builder()
				.from(BASE_MEDIA_FACTORY.getDefault(withLink, withIds))
				.setSeasonNumber(index);

		if (withLink) {
			builder.setSerie(SERIE_FACTORY.getNew())
					.addEpisode(EPISODE_SERIE_FACTORY.getNew(false, withIds))
					.addEpisode(EPISODE_SERIE_FACTORY.getNew(false, withIds));
		}

		index += 1;
		SerieSeason season = builder.build();
		if (withIds) {
			new EntityHelper().setId(season, UUID.randomUUID());
		}
		return season;
	}
}