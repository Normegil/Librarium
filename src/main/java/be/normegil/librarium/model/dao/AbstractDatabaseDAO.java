package be.normegil.librarium.model.dao;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.WarningTypes;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractDatabaseDAO<Entity> implements DAO<Entity> {

	@PersistenceContext(unitName = ApplicationProperties.PERSISTENCE_UNIT_NAME)
	private EntityManager entityManager;

	private Class<Entity> entityClass;

	public AbstractDatabaseDAO() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		entityClass = (Class<Entity>) genericSuperclass.getActualTypeArguments()[0];
	}

	@Override
	public long getNumberOfElements() {
		return (long) entityManager.createQuery(getNumberOfElementsQuery()).getSingleResult();
	}

	public List<Entity> getAll() {
		return getAll(0L, ApplicationProperties.REST.DEFAULT_LIMIT);
	}

	@Override
	public List<Entity> getAll(final long offset, final int limit) {
		return entityManager.createQuery(getGetAllQuery()).getResultList();
	}


	public Entity get(Object id) {
		return entityManager.find(getEntityClass(), id);
	}

	public void create(Entity entity) {
		entityManager.persist(entity);
	}

	public Entity update(Entity entity) {
		return entityManager.merge(entity);
	}

	public void remove(Entity entity) {
		entityManager.remove(entity);
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@SuppressWarnings(WarningTypes.UNUSED)
	protected void setEntityManager(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	protected Class<Entity> getEntityClass() {
		return entityClass;
	}

	private String getGetAllQuery() {
		return "select e from " + entityClass.getName() + " e";
	}

	private String getNumberOfElementsQuery() {
		return "select count(e.id) from " + entityClass.getName() + " e";
	}
}
