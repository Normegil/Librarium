package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.Class;
import be.normegil.librarium.model.data.Media;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;

public class UTStaffMemberSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<StaffMember> FACTORY = FactoryRepository.get(StaffMember.class);
	private static final Class<StaffMember> CLASS = new Class<>(StaffMember.class);
	private StaffMember entity;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getNew();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testBuilderConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(StaffMember.Init.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCopyConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(StaffMember.class), new Object[]{null});
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
	public void testSetMedia_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setMedia", Media.class), new Object[]{null});
	}
}