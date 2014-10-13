package be.normegil.librarium.model.data.game;

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
	private static final DataFactory<Game> GAME_FACTORY = FactoryRepository.get(Game.class);
	private static final UUID DEFAULT_ID = UUID.fromString("");

	@Override
	public GameSerie getDefault() {
		return getDefault(true, false);
	}

	@Override
	public GameSerie getNew() {
		return getNew(true, false);
	}

	@Override
	public GameSerie getDefault(final boolean withLink, final boolean withIds) {
		GameSerie.Builder builder = GameSerie.builder()
				.from(BASE_MEDIA_FACTORY.getDefault(withLink, withIds));

		if (withLink) {
			builder.addGame(GAME_FACTORY.getDefault(false, withIds));
		}

		GameSerie serie = builder.build();
		if (withIds) {
			new EntityHelper().setId(serie, DEFAULT_ID);
		}
		return serie;
	}

	@Override
	public GameSerie getNew(final boolean withLink, final boolean withIds) {
		GameSerie.Builder builder = GameSerie.builder()
				.from(BASE_MEDIA_FACTORY.getNew(withLink, withIds));

		if (withLink) {
			builder.addGame(GAME_FACTORY.getNew(false, withIds))
					.addGame(GAME_FACTORY.getNew(false, withIds));
		}

		GameSerie serie = builder.build();
		if (withLink) {
			new EntityHelper().setId(serie, UUID.randomUUID());
		}
		return serie;
	}
}