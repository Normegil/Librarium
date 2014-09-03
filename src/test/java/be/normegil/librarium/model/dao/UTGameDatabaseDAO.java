package be.normegil.librarium.model.dao;

import be.normegil.librarium.SpecificTestProperties;
import be.normegil.librarium.model.data.game.Game;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class UTGameDatabaseDAO {

	public static final String NAME = "GameName";
	public static final String ALTERNATIVE_NAME = "AlternativeName";
	public static final String ALTERNATIVE_NAME2 = "AlternativeName2";
	private EntityManager entityManager;
	private EntityManagerFactory entityManagerFactory;
	private Game game;
	private GameDatabaseDAO dao;

	@Before
	public void setUp() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory(SpecificTestProperties.PERSISTENCE_UNIT_NAME);
		entityManager = entityManagerFactory.createEntityManager();

		game = insertGameInDatabase();

		dao = new GameDatabaseDAO();
		dao.entityManager = entityManager;
	}

	@After
	public void tearDown() throws Exception {
		game = null;

		dao = null;

		entityManager.close();
		entityManager = null;

		entityManagerFactory.close();
		entityManagerFactory = null;
	}

	@Test
	public void testGetAll() throws Exception {
		Collection<Game> games = new ArrayList<Game>();
		games.add(game);
		Collection<Game> allGames = dao.getAll();
		assertTrue("Games Defined[" + games + "]\nGames Loaded[" + allGames + "]", allGames.containsAll(games));
	}

	@Test
	public void testGet() throws Exception {
		Game foundGame = dao.get(game.getId());
		assertEquals(game, foundGame);
	}

	@Test
	public void testSave_AlreadyExistingObject() throws Exception {
		game.setTitle(ALTERNATIVE_NAME2);
		dao.save(game);
		Game foundGame = dao.get(game.getId());
		assertEquals(game, foundGame);
	}

	@Test
	public void testSave() throws Exception {
		Game newGame = Game.builder().setTitle(ALTERNATIVE_NAME2).build();
		dao.save(newGame);
		Game foundGame = dao.get(newGame.getId());
		assertEquals(newGame, foundGame);
	}

	@Test
	public void testRemove() throws Exception {
		dao.remove(game);
		assertNull(dao.get(game.getId()));
	}

	private Game insertGameInDatabase() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Game game = Game.builder().setTitle(NAME).build();
		Game otherGame = Game.builder().setTitle(ALTERNATIVE_NAME).build();
		entityManager.persist(game);
		entityManager.persist(otherGame);
		transaction.commit();
		return game;
	}

}
