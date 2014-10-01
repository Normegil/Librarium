package be.normegil.librarium.model.dao;

import be.normegil.librarium.SpecificTestProperties;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.BaseMedia;
import be.normegil.librarium.model.data.Entity;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.tool.DAOHelper;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
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


public class UTDatabaseDAO {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> FACTORY = FactoryRepository.get(Game.class);
	private static final String ALTERNATIVE_TITLE = "AlternativeTitle";

	private EntityManager entityManager;
	private EntityManagerFactory entityManagerFactory;
	private DatabaseDAO dao;
	private BaseMedia entity;

	@Before
	public void setUp() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory(SpecificTestProperties.PERSISTENCE_UNIT_NAME);
		entityManager = entityManagerFactory.createEntityManager();

		entity = insertDataInDatabase();

		dao = new DatabaseDAO<>(Game.class);
		new DAOHelper().setEntityManager(dao, entityManager);
	}

	@After
	public void tearDown() throws Exception {
		entity = null;

		dao = null;

		entityManager.close();
		entityManager = null;

		entityManagerFactory.close();
		entityManagerFactory = null;
	}

	@Test
	public void testGetAll() throws Exception {
		Collection<Entity> entities = new ArrayList<>();
		entities.add(entity);
		Collection<Entity> allEntitys = dao.getAll();
		assertTrue("Entitys Defined[" + entities + "]\nEntitys Loaded[" + allEntitys + "]", allEntitys.containsAll(entities));
	}

	@Test
	public void testGetNumberOfElements() throws Exception {
		Object result = entityManager.createQuery("select count(e.id) from " + entity.getClass().getName() + " e").getSingleResult();
		long numberOfElements = dao.getNumberOfElements();
		assertEquals(result, numberOfElements);
	}

	@Test
	public void testGet() throws Exception {
		Entity foundEntity = (Entity) dao.get(entity.getId());
		assertEquals(entity, foundEntity);
	}

	@Test
	public void testSave_AlreadyExistingObject() throws Exception {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entity.setTitle(ALTERNATIVE_TITLE);
		dao.update(entity);
		transaction.commit();

		BaseMedia foundEntity = (Game) dao.get(entity.getId());
		assertEquals(ALTERNATIVE_TITLE, foundEntity.getTitle());
	}

	@Test
	public void testSave() throws Exception {
		Entity newEntity = FACTORY.getNext();

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		dao.create(newEntity);
		transaction.commit();

		Entity foundEntity = (Entity) dao.get(newEntity.getId());
		assertEquals(newEntity, foundEntity);
	}

	@Test
	public void testRemove() throws Exception {
		Object entityId = entity.getId();

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		dao.remove(entity);
		transaction.commit();

		assertNull(dao.get(entityId));
	}

	private BaseMedia insertDataInDatabase() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		BaseMedia entity = FACTORY.getNext();
		BaseMedia otherEntity = FACTORY.getNext();
		entityManager.persist(entity);
		entityManager.persist(otherEntity);
		transaction.commit();
		return entity;
	}
}
