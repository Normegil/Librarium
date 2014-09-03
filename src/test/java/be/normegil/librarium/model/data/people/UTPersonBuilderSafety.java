package be.normegil.librarium.model.data.people;

import be.normegil.librarium.libraries.Class;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.Collection;

public class UTPersonBuilderSafety {

	private static final Class<Person.Builder> CLASS = new Class<>(Person.Builder.class);
	private Person.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = Person.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("from", Person.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testEmptyBuildReturnValue() throws Exception {
		Validator.validate(entity, CLASS.getMethod("build"));
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllRoles_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllRoles", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddRole_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addRole", Role.class), new Object[]{null});
	}
}