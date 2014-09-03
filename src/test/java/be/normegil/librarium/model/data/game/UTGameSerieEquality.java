package be.normegil.librarium.model.data.game;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.AbstractDataEqualityTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTGameSerieEquality extends AbstractDataEqualityTest<GameSerie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<GameSerie> FACTORY = FactoryRepository.get(GameSerie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> GAME_FACTORY = FactoryRepository.get(Game.class);

	@Override
	protected GameSerie getNewEntity() {
		return FACTORY.getNew();
	}

	@Test
	public void testUnchanged() throws Exception {
		GameSerie entity = getEntity();
		GameSerie copy = new GameSerie(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentGame() throws Exception {
		GameSerie entity = getEntity();
		GameSerie copy = new GameSerie(entity);
		entity.addGame(GAME_FACTORY.getNext());
		assertNotEquals(entity, copy);
	}
}