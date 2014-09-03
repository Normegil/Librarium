package be.normegil.librarium.rest;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.dao.GameDAO;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.model.dao.GameTestDAO;
import be.normegil.librarium.tool.EntityHelper;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.Collection;

import static org.junit.Assert.*;

public class UTGameREST_BorderCases {

	public static final String NAME = "GameREST";
	public static final long ID = 10L;
	private GameREST gameREST;
	private GameDAO gameDAO;

	@Before
	public void setUp() throws Exception {
		gameDAO = new GameTestDAO();
		emptyDAO();

		gameREST = new GameREST();
		gameREST.setGameDAO(gameDAO);
	}

	@After
	public void tearDown() throws Exception {
		gameREST = null;
		gameDAO = null;
	}

	@Test
	public void testGetAll_NotFound() throws Exception {
		Response response = gameREST.getGamesList();
		@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
		Collection<Game> games = (Collection<Game>) response.getEntity();
		assertTrue(games.isEmpty());
	}

	@Test
	public void testGet_NotFound() throws Exception {
		Response response = gameREST.getGame(ID);
		Game game = (Game) response.getEntity();
		assertNull(game);
	}

	@Test
	public void testCreate_WithID() throws Exception {
		GameTestDAO gameTestDAO = new GameTestDAO();
		gameREST.setGameDAO(gameTestDAO);

		Game game = Game.builder().setTitle(NAME).build();
		EntityHelper entityHelper = new EntityHelper();
		entityHelper.setId(game, 1L);
		Response response = gameREST.createGame(game);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatus());
	}

	@Test
	public void testUpdateGame_NotExisting() throws Exception {
		Game game = Game.builder().setTitle(NAME).build();
		Response response = gameREST.updateGame(ID, game);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatus());
	}

	@Test
	public void testUpdateGame_DifferentsIDs() throws Exception {
		Game game = Game.builder().setTitle(NAME).build();
		Response response = gameREST.updateGame(ID, game);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatus());
	}

	@Test
	public void testDeleteGame_NotExistinng() throws Exception {
		Response response = gameREST.deleteGame(ID);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatus());
	}

	private void emptyDAO() {
		for (Game game : gameDAO.getAll()) {
			gameDAO.remove(game);
		}
	}
}
