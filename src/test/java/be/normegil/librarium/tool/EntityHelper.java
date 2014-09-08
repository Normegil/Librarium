package be.normegil.librarium.tool;

import be.normegil.librarium.model.data.Entity;

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
			throw new be.normegil.librarium.util.exception.IllegalAccessException(e);
		} catch (NoSuchFieldException e) {
			throw new be.normegil.librarium.util.exception.NoSuchFieldException(e);
		}
	}

}
