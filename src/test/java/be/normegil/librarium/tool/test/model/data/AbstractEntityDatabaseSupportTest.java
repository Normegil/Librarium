package be.normegil.librarium.tool.test.model.data;

import be.normegil.librarium.model.data.Entity;

public abstract class AbstractEntityDatabaseSupportTest<E extends Entity> extends AbstractDatabaseSupportTest<E> {

	protected AbstractEntityDatabaseSupportTest(Class<E> entityClass) {
		super(entityClass);
	}

	protected abstract E initEntity();

	protected abstract E changeEntity(final E entity);

	@Override
	protected Object getId(final E entity) {
		return entity.getId();
	}
}
