package be.normegil.librarium.tool;

import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.libraries.FieldWrapper;
import be.normegil.librarium.model.data.Entity;
import be.normegil.librarium.util.exception.IllegalAccessRuntimeException;
import be.normegil.librarium.util.exception.NoSuchFieldRuntimeException;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.UUID;

public class EntityHelper {

	public void setId(final Entity entity, final UUID id) {
		try {
			Field idField = Entity.class.getDeclaredField("id");

			boolean accessible = idField.isAccessible();
			idField.setAccessible(true);

			idField.set(entity, id);

			idField.setAccessible(accessible);

		} catch (IllegalAccessException e) {
			throw new IllegalAccessRuntimeException(e);
		} catch (NoSuchFieldException e) {
			throw new NoSuchFieldRuntimeException(e);
		}
	}

	public void assignIdsTo(final Collection<? extends Entity> entities) {
		for (Entity entity : entities) {
			assignIdTo(entity);
		}
	}

	public void assignIdTo(final Entity entities) {
		setId(entities, UUID.randomUUID());
	}
}
