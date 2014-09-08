package be.normegil.librarium.tool.test.model.data;

import be.normegil.librarium.SpecificTestProperties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public abstract class AbstractDatabaseSupportTest<E> {

	private EntityManager entityManager;
	private EntityManagerFactory entityManagerFactory;
	private final Class<E> entityClass;
	private E entity;

	protected AbstractDatabaseSupportTest(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	protected abstract E initEntity();

	protected abstract E changeEntity(final E entity);

	protected abstract Object getId(E entity);

	@Before
	public void setUp() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory(SpecificTestProperties.PERSISTENCE_UNIT_NAME);
		entityManager = entityManagerFactory.createEntityManager();

		entity = insertEntityInDatabase();
	}

	@Test
	public void testCreate() throws Exception {
		E loadedEntity = entityManager.find(entityClass, getId(entity));
		assertEquals(entity, loadedEntity);
	}

	@Test
	public void testUpdate() throws Exception {
		EntityTransaction transaction = entityManager.getTransaction();
		E loadedEntity = entityManager.find(entityClass, getId(entity));
		transaction.begin();
		changeEntity(entity);
		transaction.commit();

		E alternativeLoadedEntity = entityManager.find(entityClass, getId(entity));
		assertEquals(loadedEntity, alternativeLoadedEntity);
	}

	@Test
	public void testDelete() throws Exception {
		entityManager.remove(entity);
		E loadedEntity = entityManager.find(entityClass, getId(entity));
		assertNull(loadedEntity);
	}

	@After
	public void tearDown() throws Exception {
		entityManager.close();
		entityManagerFactory.close();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	protected E insertEntityInDatabase() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		E entity = initEntity();
		entityManager.persist(entity);
		transaction.commit();
		return entity;
	}

}
