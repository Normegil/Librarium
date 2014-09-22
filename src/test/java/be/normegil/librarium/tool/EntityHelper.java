package be.normegil.librarium.tool;

import be.normegil.librarium.model.data.Entity;
import be.normegil.librarium.util.exception.IllegalAccessRuntimeException;
import be.normegil.librarium.util.exception.NoSuchFieldRuntimeException;

import java.lang.reflect.Field;
import java.util.UUID;

public class EntityHelper {

	public void setId(Entity entity, UUID id) {
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

}
