package be.normegil.librarium.model.data.people;

import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;

public class UTEnterpriseBuilderSafety {

	private static final ClassWrapper<Enterprise.Builder> CLASS = new ClassWrapper<>(Enterprise.Builder.class);
	private Enterprise.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = Enterprise.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("from", Enterprise.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testEmptyBuildReturnValue() throws Exception {
		Validator.validate(entity, CLASS.getMethod("build"));
	}
}