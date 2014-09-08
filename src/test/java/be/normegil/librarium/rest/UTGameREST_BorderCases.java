package be.normegil.librarium.rest;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.dao.DAO;
import be.normegil.librarium.model.dao.GameTestDAO;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.tool.EntityHelper;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.UUID;

import static org.junit.Assert.*;

public class UTGameREST_BorderCases {

	private static final String NAME = "GameREST";
	private static final UUID ID = UUID.fromString("72e608ea-202c-44aa-ae42-699130d8367c");
	private static final UUID ALTERNATIVE_ID = UUID.fromString("40d46c43-0700-4f38-8f4a-dcfa8186195e");
	private GameREST gameREST;
	private DAO<Game> gameDAO;

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
		entityHelper.setId(game, ALTERNATIVE_ID);
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
