package be.normegil.librarium.model.data.game;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.data.BaseMedia;
import be.normegil.librarium.model.data.DownloadLink;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTGameSerieSafety.class,
		UTGameSerie.class,
		UTGameSerieEquality.class,
		UTGameSerieComparator.class,
		UTGameSerieBuilderSafety.class,
		UTGameSerieBuilder.class,
		UTGameSerieDatabaseSupport.class
})
public class GameSerieTestSuite implements DataFactory<GameSerie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BaseMedia> BASE_MEDIA_FACTORY = FactoryRepository.get(BaseMedia.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<DownloadLink> DOWNLOAD_LINK_FACTORY = FactoryRepository.get(DownloadLink.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> GAME_FACTORY = FactoryRepository.get(Game.class);
	private static final String TITLE = "Title";
	private static final String DESCRIPTION = "Description";
	private static final String TAG = "Tag";
	private static long index = 0L;

	@Override
	public GameSerie getNew() {
		return getNew(true);
	}

	@Override
	public GameSerie getNext() {
		return getNext(true);
	}

	@Override
	public GameSerie getNew(boolean withLink) {
		GameSerie.Builder builder = GameSerie.builder()
				.from(BASE_MEDIA_FACTORY.getNew(withLink));

		if (withLink) {
			builder.addGame(GAME_FACTORY.getNew(false));
		}

		return builder.build();
	}

	@Override
	public GameSerie getNext(boolean withLink) {
		GameSerie.Builder builder = GameSerie.builder()
				.from(BASE_MEDIA_FACTORY.getNext(withLink));

		if (withLink) {
			builder.addGame(GAME_FACTORY.getNext(false))
					.addGame(GAME_FACTORY.getNext(false));
		}

		return builder.build();
	}
}