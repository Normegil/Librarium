package be.normegil.librarium.tool.test.model.dao;

import be.normegil.librarium.SpecificTestProperties;
import be.normegil.librarium.model.dao.DatabaseDAO;
import be.normegil.librarium.tool.DAOHelper;
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

public abstract class AbstractDAOTest<DAO extends DatabaseDAO, Entity> {

	private EntityManager entityManager;
	private EntityManagerFactory entityManagerFactory;
	private DAO dao;
	private Entity entity;

	@Before
	public void setUp() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory(SpecificTestProperties.PERSISTENCE_UNIT_NAME);
		entityManager = entityManagerFactory.createEntityManager();

		entity = insertDataInDatabase();

		dao = initDAO();
		new DAOHelper().setEntityManager(dao, entityManager);
	}

	protected abstract DAO initDAO();

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
		Entity foundEntity = (Entity) dao.get(getEntityId(entity));
		assertEquals(entity, foundEntity);
	}

	protected abstract Object getEntityId(Entity entity);

	@Test
	public void testSave_AlreadyExistingObject() throws Exception {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		changeEntity(entity);
		Object databaseEntity = dao.update(entity);
		transaction.commit();

		Entity foundEntity = (Entity) dao.get(getEntityId((Entity) databaseEntity));
		assertChangedPropertyEquals(foundEntity);
	}

	protected abstract void assertChangedPropertyEquals(final Entity foundEntity);

	protected abstract void changeEntity(final Entity entity);

	@Test
	public void testSave() throws Exception {
		Entity newEntity = getNewData();

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		dao.create(newEntity);
		transaction.commit();

		Entity foundEntity = (Entity) dao.get(getEntityId(newEntity));
		assertEquals(newEntity, foundEntity);
	}

	@Test
	public void testRemove() throws Exception {
		Object entityId = getEntityId(entity);

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		dao.remove(entity);
		transaction.commit();

		assertNull(dao.get(entityId));
	}

	private Entity insertDataInDatabase() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Entity entity = getNewData();
		Entity otherEntity = getNewData();
		entityManager.persist(entity);
		entityManager.persist(otherEntity);
		transaction.commit();
		return entity;
	}

	protected abstract Entity getNewData();

}
