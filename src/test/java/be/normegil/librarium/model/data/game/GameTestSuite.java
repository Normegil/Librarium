package be.normegil.librarium.model.data.game;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.Media;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.UUID;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTGameSafety.class,
		UTGame.class,
		UTGameEquality.class,
		UTGameComparator.class,
		UTGameBuilderSafety.class,
		UTGameBuilder.class,
		UTGameDigestSafety.class,
		UTGameDigest.class,
		UTGameDigestEquality.class,
		UTGameDigestJSONSupport.class,
		UTGameDatabaseSupport.class
})
public class GameTestSuite implements DataFactory<Game> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> MEDIA_FACTORY = FactoryRepository.get(Media.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<GameSerie> GAME_SERIE_FACTORY = FactoryRepository.get(GameSerie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);
	private static final UUID DEFAULT_ID = UUID.fromString("");

	@Override
	public Game getDefault() {
		return getDefault(true, false);
	}

	@Override
	public Game getNew() {
		return getNew(true, false);
	}

	@Override
	public Game getDefault(final boolean withLink, final boolean withIds) {
		Game.Builder builder = Game.builder()
				.from(MEDIA_FACTORY.getDefault(withLink, withIds));
		if (withLink) {
			builder
					.addDeveloper(RESPONSIBLE_FACTORY.getDefault(false, withIds))
					.addEditor(RESPONSIBLE_FACTORY.getDefault(false, withIds))
					.addComposer(RESPONSIBLE_FACTORY.getDefault(false, withIds))
					.setSerie(GAME_SERIE_FACTORY.getDefault(false, withIds));
		}
		Game game = builder.build();
		if (withIds) {
			new EntityHelper().setId(game, DEFAULT_ID);
		}
		return game;
	}

	@Override
	public Game getNew(final boolean withLink, final boolean withIds) {
		Game.Builder builder = Game.builder()
				.from(MEDIA_FACTORY.getNew(withLink, withIds));
		if (withLink) {
			builder
					.addDeveloper(RESPONSIBLE_FACTORY.getNew(false, withIds))
					.addEditor(RESPONSIBLE_FACTORY.getNew(false, withIds))
					.addComposer(RESPONSIBLE_FACTORY.getNew(false, withIds))
					.setSerie(GAME_SERIE_FACTORY.getNew(false, withIds));
		}
		Game game = builder.build();
		if (withIds) {
			new EntityHelper().setId(game, UUID.randomUUID());
		}
		return game;
	}
}