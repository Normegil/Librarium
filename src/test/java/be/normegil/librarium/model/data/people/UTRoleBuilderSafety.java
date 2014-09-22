package be.normegil.librarium.model.data.people;

import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.model.data.video.Video;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;

public class UTRoleBuilderSafety {

	private static final ClassWrapper<Role.Builder> CLASS = new ClassWrapper<>(Role.Builder.class);
	private Role.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = Role.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("from", Role.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testEmptyBuildReturnValue() throws Exception {
		Validator.validate(entity, CLASS.getMethod("build"));
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