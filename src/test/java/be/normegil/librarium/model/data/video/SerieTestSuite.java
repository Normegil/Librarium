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

	@Override
	public Serie getNew() {
		return getNew(true);
	}

	@Override
	public Serie getNext() {
		return getNext(true);
	}

	@Override
	public Serie getNew(boolean withLink) {
		Serie.Builder builder = Serie.builder()
				.from(BASE_MEDIA_FACTORY.getNew(withLink));
		if (withLink) {
			builder.addSeason(SERIE_SEASON_FACTORY.getNew(false));
		}
		return builder.build();
	}

	@Override
	public Serie getNext(boolean withLink) {
		Serie.Builder builder = Serie.builder()
				.from(BASE_MEDIA_FACTORY.getNext(withLink));
		if (withLink) {
			builder.addSeason(SERIE_SEASON_FACTORY.getNext(false))
					.addSeason(SERIE_SEASON_FACTORY.getNext(false));
		}
		return builder.build();
	}
}