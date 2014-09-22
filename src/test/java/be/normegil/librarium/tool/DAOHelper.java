package be.normegil.librarium.tool;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.dao.AbstractDatabaseDAO;
import be.normegil.librarium.util.exception.IllegalAccessRuntimeException;
import be.normegil.librarium.util.exception.InvocationTargetRuntimeException;
import be.normegil.librarium.util.exception.NoSuchMethodRuntimeException;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DAOHelper {

	public void setEntityManager(AbstractDatabaseDAO dao, EntityManager entityManager) {
		try {
			@SuppressWarnings(WarningTypes.UNCHECKED_CALL)
			Method setEntityManager = AbstractDatabaseDAO.class.getDeclaredMethod("setEntityManager", EntityManager.class);

			boolean accessible = setEntityManager.isAccessible();
			setEntityManager.setAccessible(true);

			setEntityManager.invoke(dao, entityManager);

			setEntityManager.setAccessible(accessible);
		} catch (NoSuchMethodException e) {
			throw new NoSuchMethodRuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new InvocationTargetRuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalAccessRuntimeException(e);
		}
	}

}
