package be.normegil.librarium.rest;

import be.normegil.librarium.model.data.game.Game;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class UTGameREST_Safety {

	public static final String NAME = "Name";
	public static final UUID ID = UUID.fromString("40d46c43-0700-4f38-8f4a-dcfa8186195e");
	private GameREST gameREST;

	@Before
	public void setUp() throws Exception {
		gameREST = new GameREST();
	}

	@Test
	public void testGet_Null() throws Exception {
		Response response = gameREST.getGame(null);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatus());
	}

	@Test
	public void testCreateGame_Null() throws Exception {
		Response response = gameREST.createGame(null);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatus());
	}

	@Test
	public void testRemoveGame_Null() throws Exception {
		Response response = gameREST.deleteGame(null);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatus());
	}

	@Test
	public void testUpdateGame_NullId() throws Exception {
		Response response = gameREST.updateGame(ID, null);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatus());
	}

	@Test
	public void testUpdateGame_NullName() throws Exception {
		Game game = Game.builder().setTitle(NAME).build();
		Response response = gameREST.updateGame(null, game);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatus());
	}
}
