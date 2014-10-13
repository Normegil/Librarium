package be.normegil.librarium.model.data.game;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTGameSerieBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<GameSerie> GAME_SERIE_FACTORY = FactoryRepository.get(GameSerie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> GAME_FACTORY = FactoryRepository.get(Game.class);
	private GameSerie.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = GameSerie.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFrom() throws Exception {
		GameSerie gameSerie = GAME_SERIE_FACTORY.getNew();
		GameSerie copy = entity.from(gameSerie).build();
		assertEquals(gameSerie, copy);
	}

	@Test
	public void testAddAllGames() throws Exception {
		Collection<Game> toAdd = new HashSet<>();
		toAdd.add(GAME_FACTORY.getNew());
		toAdd.add(GAME_FACTORY.getNew());
		toAdd.add(GAME_FACTORY.getNew());

		GameSerie gameSerie = entity
				.addAllGames(toAdd)
				.build();

		assertTrue(gameSerie.getGames().containsAll(toAdd));
	}

	@Test
	public void testAddGame() throws Exception {
		Game game = GAME_FACTORY.getNew();
		GameSerie gameSerie = entity
				.addGame(game)
				.build();

		assertTrue(gameSerie.getGames().contains(game));
	}
}