package be.normegil.librarium.tool;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.Entity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EntityHelper {

	public void setId(Entity entity, Long id) {
		try {
			@SuppressWarnings(WarningTypes.UNCHECKED_CALL)
			Method setId = Entity.class.getDeclaredMethod("setId", Long.class);

			boolean accessible = setId.isAccessible();
			setId.setAccessible(true);

			setId.invoke(entity, id);

			setId.setAccessible(accessible);
		} catch (NoSuchMethodException e) {
			throw new be.normegil.librarium.util.exception.NoSuchMethodException(e);
		} catch (InvocationTargetException e) {
			throw new be.normegil.librarium.util.exception.InvocationTargetException(e);
		} catch (IllegalAccessException e) {
			throw new be.normegil.librarium.util.exception.IllegalAccessException(e);
		}
	}

}
