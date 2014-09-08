package be.normegil.librarium.rest;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.dao.DAO;
import be.normegil.librarium.model.dao.GameTestDAO;
import be.normegil.librarium.model.data.game.Game;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

import static org.junit.Assert.*;

public class UTGameREST {

	public static final String ALTERNATIVE_NAME = "GameREST";
	private GameREST gameREST;
	private DAO<Game> gameDAO;

	@Before
	public void setUp() throws Exception {
		gameDAO = new GameTestDAO();
		gameREST = new GameREST();
		gameREST.setGameDAO(gameDAO);
	}

	@After
	public void tearDown() throws Exception {
		gameREST = null;
		gameDAO = null;
	}

	@Test
	public void testGetAll() throws Exception {
		Response response = gameREST.getGamesList();
		@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
		Collection<Game> gamesList = (Collection<Game>) response.getEntity();
		Collection<Game> allInMemoryGame = gameDAO.getAll();
		assertEquals(allInMemoryGame.size(), gamesList.size());
		assertTrue(allInMemoryGame.containsAll(gamesList));
	}

	@Test
	public void testGet() throws Exception {
		Game game = getGameFromDAO();
		Response response = gameREST.getGame(game.getId());
		Game gameFromREST = (Game) response.getEntity();
		assertEquals(game, gameFromREST);
	}

	@Test
	public void testCreateGame() throws Exception {
		Game game = Game.builder()
				.setTitle(ALTERNATIVE_NAME)
				.build();
		gameREST.createGame(game);
		assertGameCreated();
	}

	@Test
	public void testUpdateGame() throws Exception {
		Game gameFromDAO = getGameFromDAO();
		gameFromDAO.setTitle(ALTERNATIVE_NAME);
		gameREST.updateGame(gameFromDAO.getId(), gameFromDAO);
		Game game = gameDAO.get(gameFromDAO.getId());
		assertEquals(ALTERNATIVE_NAME, game.getTitle());
	}

	@Test
	public void testDeleteGame() throws Exception {
		Game gameFromDAO = getGameFromDAO();
		UUID id = gameFromDAO.getId();
		gameREST.deleteGame(id);
		assertNull(gameDAO.get(id));
	}

	private void assertGameCreated() {
		boolean found = false;
		for (Game game : gameDAO.getAll()) {
			if (ALTERNATIVE_NAME.equals(game.getTitle())) {
				found = true;
			}
		}
		if (!found) {
			throw new AssertionError("Game not found [Name = " + ALTERNATIVE_NAME + "]");
		}
	}

	private Game getGameFromDAO() {
		Collection<Game> allInMemoryGame = gameDAO.getAll();
		Iterator<Game> gameIterator = allInMemoryGame.iterator();
		return gameIterator.next();
	}
}
