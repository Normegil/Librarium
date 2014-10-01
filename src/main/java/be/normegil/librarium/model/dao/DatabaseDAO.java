package be.normegil.librarium.model.dao;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.WarningTypes;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class DatabaseDAO<E> implements DAO<E> {

	@PersistenceContext(unitName = ApplicationProperties.PERSISTENCE_UNIT_NAME)
	private EntityManager entityManager;

	private Class<E> entityClass;

	public DatabaseDAO(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public long getNumberOfElements() {
		return (long) entityManager.createQuery(getNumberOfElementsQuery()).getSingleResult();
	}

	public List<E> getAll() {
		return getAll(0L, ApplicationProperties.REST.DEFAULT_LIMIT);
	}

	@Override
	public List<E> getAll(final long offset, final int limit) {
		return entityManager.createQuery(getGetAllQuery()).getResultList();
	}


	public E get(Object id) {
		return entityManager.find(getEntityClass(), id);
	}

	public void create(E entity) {
		entityManager.persist(entity);
	}

	public E update(E entity) {
		return entityManager.merge(entity);
	}

	public void remove(E entity) {
		entityManager.remove(entity);
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@SuppressWarnings(WarningTypes.UNUSED)
	protected void setEntityManager(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	protected Class<E> getEntityClass() {
		return entityClass;
	}

	private String getGetAllQuery() {
		return "select e from " + getEntityClass().getName() + " e";
	}

	private String getNumberOfElementsQuery() {
		return "select count(e.id) from " + getEntityClass().getName() + " e";
	}
}
