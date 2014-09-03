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

public class UTGameSerie {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<GameSerie> FACTORY = FactoryRepository.get(GameSerie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> GAME_FACTORY = FactoryRepository.get(Game.class);
	private GameSerie entity;
	private Collection<Game> games;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getNew();
		games = entity.getGames();
	}

	@After
	public void tearDown() throws Exception {
		games = null;
		entity = null;
	}

	@Test
	public void testCopyConstructor() throws Exception {
		GameSerie copy = new GameSerie(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testAddAllGames() throws Exception {
		Collection<Game> toAdd = new HashSet<>();
		toAdd.add(GAME_FACTORY.getNext());
		toAdd.add(GAME_FACTORY.getNext());
		toAdd.add(GAME_FACTORY.getNext());

		games.addAll(toAdd);
		entity.addAllGames(toAdd);
		assertEquals(games, entity.getGames());
	}

	@Test
	public void testAddGame() throws Exception {
		Game toAdd = GAME_FACTORY.getNext();
		games.add(toAdd);
		entity.addGame(toAdd);
		assertEquals(games, entity.getGames());
	}

	@Test
	public void testRemoveAllGames() throws Exception {
		Game base = GAME_FACTORY.getNext();
		Game second = GAME_FACTORY.getNext();
		Game third = GAME_FACTORY.getNext();

		Collection<Game> toAdd = new HashSet<>();
		toAdd.add(base);
		toAdd.add(second);
		toAdd.add(third);

		Collection<Game> toRemove = new HashSet<>();
		toRemove.add(second);
		toRemove.add(third);

		games.addAll(toAdd);
		entity.addAllGames(toAdd);

		games.removeAll(toRemove);
		entity.removeAllGames(toRemove);

		assertEquals(games, entity.getGames());
	}

	@Test
	public void testRemoveGame() throws Exception {
		Game toRemove = entity.getGames().iterator().next();
		games.remove(toRemove);
		entity.removeGame(toRemove);

		assertEquals(games, entity.getGames());
	}
}
