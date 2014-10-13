package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.model.data.video.Video;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;

public class UTRoleSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Role> FACTORY = FactoryRepository.get(Role.class);
	private static final ClassWrapper<Role> CLASS = new ClassWrapper<>(Role.class);
	private Role entity;

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
		Validator.validate(CLASS.getConstructor(Role.Init.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCopyConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(Role.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetRole_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setRole", Person.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetActor_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setActor", Person.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetVideo_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setVideo", Video.class), new Object[]{null});
	}
}