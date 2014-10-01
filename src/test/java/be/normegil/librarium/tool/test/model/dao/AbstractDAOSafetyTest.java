package be.normegil.librarium.tool.test.model.dao;

import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.model.dao.DAO;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.Set;

public abstract class AbstractDAOSafetyTest<E extends DAO> {
	private final ClassWrapper<E> daoClassToTest;
	private E dao;

	protected AbstractDAOSafetyTest(Class<E> daoClassSupported) {
		daoClassToTest = new ClassWrapper<>(daoClassSupported);
	}

	@Before
	public void setUp() throws Exception {
		dao = initDAO();
	}

	protected abstract E initDAO();

	@After
	public void tearDown() throws Exception {
		dao = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testGet_Null() throws Exception {
		Validator.validate(dao, daoClassToTest.getMethod("get", Object.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSave_Null() throws Exception {
		Validator.validate(dao, daoClassToTest.getMethod("create", Object.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testUpdate_Null() throws Exception {
		Validator.validate(dao, daoClassToTest.getMethod("update", Object.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemove_Null() throws Exception {
		Validator.validate(dao, daoClassToTest.getMethod("remove", Object.class), new Object[]{null});
	}
}
