package be.normegil.librarium.rest.game;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.dao.DAO;
import be.normegil.librarium.model.dao.MemoryTestDAO;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

import static org.junit.Assert.*;

public class UTGameREST {

	public static final String ALTERNATIVE_NAME = "GameREST";
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> GAME_FACTORY = FactoryRepository.get(Game.class);
	private GameREST gameREST;
	private DAO<Game> gameDAO;

	@Mock
	private UriInfo info;

	@Before
	public void setUp() throws Exception {
		gameDAO = new MemoryTestDAO<>(Game.class);
		gameREST = new GameREST();
		gameREST.setDAO(gameDAO);

		Mockito.when(info.getBaseUri()).thenReturn(URL_FACTORY.getNext().toURI());
	}

	@After
	public void tearDown() throws Exception {
		gameREST = null;
		gameDAO = null;
	}

	@Test
	public void testGetAll_NullOffset_NullLimit() throws Exception {
		Response response = gameREST.getAll(info, null, null);
		@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
		Collection<Game> gamesList = (Collection<Game>) response.getEntity();
		Collection<Game> allInMemoryGame = gameDAO.getAll();
		assertEquals(allInMemoryGame.size(), gamesList.size());
		assertTrue(allInMemoryGame.containsAll(gamesList));
		throw new NotImplementedException();
	}

	@Test
	public void testGetAll_GivenOffset_NullLimit() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testGetAll_NullOffset_GivenLimit() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testGetAll_GivenOffset_GivenLimit() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testGetAll_NegativeOffset() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testGetAll_OffsetTooHigh() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testGet() throws Exception {
		Game game = getGameFromDAO();
		Response response = gameREST.get(info, game.getId());
		Game gameFromREST = (Game) response.getEntity();
		assertEquals(game, gameFromREST);
		throw new NotImplementedException();
	}

	@Test
	public void testGet_IDNotFound() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testCreate() throws Exception {
		gameREST.create(info, GAME_FACTORY.getNext());
		assertGameCreated();
		throw new NotImplementedException();
	}

	@Test
	public void testCreate_WithID() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPUT_ChangeTitle() throws Exception {
		Game gameFromDAO = getGameFromDAO();
		gameFromDAO.setTitle(ALTERNATIVE_NAME);
		gameREST.updateByPUT(gameFromDAO.getId(), gameFromDAO);
		Game game = gameDAO.get(gameFromDAO.getId());
		assertEquals(ALTERNATIVE_NAME, game.getTitle());
	}

	@Test
	public void testUpdateByPUT_ChangeDescription() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPUT_ChangeOfficialWebsite() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPUT_ChangeWikipediaPage() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPUT_ChangeGameSerie() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPUT_ChangeTags() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPUT_ChangeStores() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPUT_ChangeDownloadLink() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPUT_ChangeUniverses() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPUT_ChangeSupports() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPUT_ChangeReleaseDates() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPUT_ChangeDeveloppers() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPUT_ChangeEditors() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPUT_ChangeComposers() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPUT_NotExisting() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPUT_DifferentsIDs() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPOST_ChangeTitle() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPOST_ChangeDescription() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPOST_ChangeOfficialWebsite() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPOST_ChangeWikipediaPage() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPOST_ChangeGameSerie() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPOST_ChangeTags() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPOST_ChangeStores() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPOST_ChangeDownloadLink() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPOST_ChangeUniverses() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPOST_ChangeSupports() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPOST_ChangeReleaseDates() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPOST_ChangeDeveloppers() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPOST_ChangeEditors() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPOST_ChangeComposers() throws Exception {
		throw new NotImplementedException();
	}



	@Test
	public void testUpdateByPOST_NotExisting() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testUpdateByPOST_DifferentsIDs() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testDelete() throws Exception {
		Game gameFromDAO = getGameFromDAO();
		UUID id = gameFromDAO.getId();
		gameREST.delete(id);
		assertNull(gameDAO.get(id));
	}

	@Test
	public void testDelete_IDNotFound() throws Exception {
		gameREST.delete(UUID.randomUUID());
		throw new NotImplementedException();
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
