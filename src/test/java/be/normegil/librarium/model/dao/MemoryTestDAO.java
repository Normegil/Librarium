package be.normegil.librarium.model.dao;

import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.model.data.Entity;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import org.apache.commons.lang3.Validate;

import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Stateless
public class MemoryTestDAO<E extends Entity> implements DAO<E> {

	private static final int DEFAULT_NUMBER_OF_ENTITY = 5;
	private List<E> entities = new ArrayList<>();
	private DataFactory<E> FACTORY;

	public MemoryTestDAO(Class<E> daoClassSupported) {
		this(DEFAULT_NUMBER_OF_ENTITY, daoClassSupported);
	}

	public MemoryTestDAO(int numberOfEntityToAdd, Class<E> daoClassSupported) {
		add(numberOfEntityToAdd);
		FACTORY = FactoryRepository.get(daoClassSupported);
	}

	@Override
	public List<E> getAll() {
		return new ArrayList<>(entities);
	}

	@Override
	public List<E> getAll(final long offset, final int limit) {
		List<E> entitiesToReturn = new ArrayList<>();
		for (int i = 0; i < limit && offset + i < entities.size(); i++) {
			int index = (int) (offset + i);
			entitiesToReturn.add(entities.get(index));
		}
		return entitiesToReturn;
	}

	@Override
	public long getNumberOfElements() {
		return entities.size();
	}

	@Override
	public E get(final Object id) {
		Validate.notNull(id);
		for (E entity : entities) {
			if (entity.getId().equals(id)) {
				return entity;
			}
		}
		return null;
	}

	@Override
	public void create(@NotNull @Valid final E entity) {
		entities.add(entity);
		new EntityHelper().setId(entity, UUID.randomUUID());
	}

	@Override
	public E update(@NotNull @Valid final E entity) {
		remove(entity);
		create(entity);
		return entity;
	}

	@Override
	public void remove(@NotNull @Valid final E entity) {
		E foundEntity = null;
		for (E existingEntity : entities) {
			if (existingEntity.getId().equals(entity.getId())) {
				foundEntity = existingEntity;
				break;
			}
		}
		entities.remove(foundEntity);
	}

	private EntityHelper add(int numberOfEntity) {
		EntityHelper entityHelper = new EntityHelper();
		for (int i = 0; i < numberOfEntity; i++) {
			E entity = FACTORY.getNext();
			entityHelper.setId(entity, UUID.randomUUID());
			entities.add(entity);
		}
		return entityHelper;
	}
}
