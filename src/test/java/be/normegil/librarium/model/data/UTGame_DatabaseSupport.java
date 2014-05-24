package be.normegil.librarium.model.data;

import be.normegil.librarium.SpecificTestProperties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static be.normegil.librarium.model.data.GameTestHelper.assertGamePropertiesEquals;
import static org.junit.Assert.assertNull;

public class UTGame_DatabaseSupport {

    public static final String NAME = "GameName";
    public static final String ALTERNATIVE_NAME = "AlternativeName";
    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;
    private Game game;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory(SpecificTestProperties.PERSISTENCE_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();

        game = insertGameInDatabase();
    }

    @Test
    public void testCreate() throws Exception {
        Game loadedGame = entityManager.find(Game.class, game.getId());
        assertGamePropertiesEquals(game, loadedGame);
    }

    @Test
    public void testUpdate() throws Exception {
        EntityTransaction transaction = entityManager.getTransaction();
        Game loadedGame = entityManager.find(Game.class, game.getId());
        transaction.begin();
        loadedGame.setName(ALTERNATIVE_NAME);
        transaction.commit();

        Game alternativeLoadedGame = entityManager.find(Game.class, loadedGame.getId());
        assertGamePropertiesEquals(loadedGame, alternativeLoadedGame);
    }

    @Test
    public void testDelete() throws Exception {


        entityManager.remove(game);
        Long idGame = game.getId();
        Game loadedGame = entityManager.find(Game.class, idGame);
        assertNull(loadedGame);
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    private Game insertGameInDatabase() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Game game = new Game(NAME);
        entityManager.persist(game);
        transaction.commit();
        return game;
    }
}
