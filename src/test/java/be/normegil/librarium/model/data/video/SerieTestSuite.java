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
		UTSerieSafety.class,
		UTSerie.class,
		UTSerieEquality.class,
		UTSerieComparator.class,
		UTSerieBuilderSafety.class,
		UTSerieBuilder.class,
		UTSerieDatabaseSupport.class
})
public class SerieTestSuite implements DataFactory<Serie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BaseMedia> BASE_MEDIA_FACTORY = FactoryRepository.get(BaseMedia.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<SerieSeason> SERIE_SEASON_FACTORY = FactoryRepository.get(SerieSeason.class);
	private static final UUID DEFAULT_ID = UUID.fromString("");

	@Override
	public Serie getDefault() {
		return getDefault(true, false);
	}

	@Override
	public Serie getNew() {
		return getNew(true, false);
	}

	@Override
	public Serie getDefault(final boolean withLink, final boolean withIds) {
		Serie.Builder builder = Serie.builder()
				.from(BASE_MEDIA_FACTORY.getDefault(withLink, withIds));
		if (withLink) {
			builder.addSeason(SERIE_SEASON_FACTORY.getDefault(false, withIds));
		}
		Serie serie = builder.build();
		if (withIds) {
			new EntityHelper().setId(serie, DEFAULT_ID);
		}
		return serie;
	}

	@Override
	public Serie getNew(final boolean withLink, final boolean withIds) {
		Serie.Builder builder = Serie.builder()
				.from(BASE_MEDIA_FACTORY.getNew(withLink, withIds));
		if (withLink) {
			builder.addSeason(SERIE_SEASON_FACTORY.getNew(false, withIds))
					.addSeason(SERIE_SEASON_FACTORY.getNew(false, withIds));
		}
		Serie serie = builder.build();
		if (withIds) {
			new EntityHelper().setId(serie, DEFAULT_ID);
		}
		return serie;
	}
}