package be.normegil.librarium.model.data.game;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.Media;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.TestResult;
import be.normegil.librarium.tool.comparator.PropertyComparatorHelper;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTGameSafety.class,
		UTGame.class,
		UTGameEquality.class,
		UTGameComparator.class,
		UTGameBuilderSafety.class,
		UTGameBuilder.class,
		UTGameDatabaseSupport.class
})
public class GameTestSuite implements DataFactory<Game> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> MEDIA_FACTORY = FactoryRepository.get(Media.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<GameSerie> GAME_SERIE_FACTORY = FactoryRepository.get(GameSerie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);

	@Override
	public Game getNew() {
		return getNew(true);
	}

	@Override
	public Game getNext() {
		return getNext(true);
	}

	@Override
	public Game getNew(boolean withLink) {
		Game.Builder builder = Game.builder()
				.from(MEDIA_FACTORY.getNew(withLink));
		if (withLink) {
			builder
					.addDeveloper(RESPONSIBLE_FACTORY.getNew(false))
					.addEditor(RESPONSIBLE_FACTORY.getNew(false))
					.addComposer(RESPONSIBLE_FACTORY.getNew(false))
					.setSerie(GAME_SERIE_FACTORY.getNew(false));
		}
		return builder.build();
	}

	@Override
	public Game getNext(boolean withLink) {
		Game.Builder builder = Game.builder()
				.from(MEDIA_FACTORY.getNext(withLink));
		if (withLink) {
			builder
					.addDeveloper(RESPONSIBLE_FACTORY.getNext(false))
					.addEditor(RESPONSIBLE_FACTORY.getNext(false))
					.addComposer(RESPONSIBLE_FACTORY.getNext(false))
					.setSerie(GAME_SERIE_FACTORY.getNext(false));
		}
		return builder.build();
	}
}