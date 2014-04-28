package be.normegil.librarium.model.dao;

import be.normegil.librarium.TestSpecificProperties;
import be.normegil.librarium.model.data.Game;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.assertNull;

public class GameDatabaseDAOTestSafety {

    public static final String NAME = "GameName";
    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;
    private GameDatabaseDAO dao;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory(TestSpecificProperties.PERSISTENCE_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();

        insertGameInDatabase();

        dao = new GameDatabaseDAO();
        dao.entityManager = entityManager;
    }

    @Test(expected = NullPointerException.class)
    public void testGetGame_Null() throws Exception {
        dao.get(null);
    }

    @Test
    public void testGet_FakeID() throws Exception {
        assertNull(dao.get(42L));
    }

    @Test(expected = NullPointerException.class)
    public void testSave_Null() throws Exception {
        dao.save(null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveGame_Null() throws Exception {
        dao.remove(null);
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
