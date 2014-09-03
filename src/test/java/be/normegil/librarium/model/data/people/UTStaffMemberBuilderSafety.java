package be.normegil.librarium.model.data.people;

import be.normegil.librarium.libraries.Class;
import be.normegil.librarium.model.data.Media;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;

public class UTStaffMemberBuilderSafety {

	private static final Class<StaffMember.Builder> CLASS = new Class<>(StaffMember.Builder.class);
	private StaffMember.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = StaffMember.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("from", StaffMember.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testEmptyBuildReturnValue() throws Exception {
		Validator.validate(entity, CLASS.getMethod("build"));
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetRole_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setRole", StaffRole.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetResponsible_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setResponsible", Responsible.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetVideo_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setMedia", Media.class), new Object[]{null});
	}
}