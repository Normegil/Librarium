package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.Collection;

public class UTPersonSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Person> FACTORY = FactoryRepository.get(Person.class);
	private static final ClassWrapper<Person> CLASS = new ClassWrapper<>(Person.class);
	private Person entity;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getDefault();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testBuilderConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(Person.Init.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCopyConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(Person.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllRoles_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllRoles", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddRole_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addRole", Role.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveAllRoles_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeAllRoles", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveRole_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeRole", Role.class), new Object[]{null});
	}
}