package be.normegil.librarium.util;

import be.normegil.librarium.libraries.Class;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;

public class UTClassHelperSafety {

	private static final Class<ClassHelper> CLASS = new Class<>(ClassHelper.class);
	private ClassHelper entity;

	@Before
	public void setUp() throws Exception {
		entity = new ClassHelper();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testGetAllFields_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("getAllFields", java.lang.Class.class), new Object[]{null});
	}
}