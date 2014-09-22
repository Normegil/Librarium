package be.normegil.librarium.model.data.people;

import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.data.fake.FakeResponsible;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;

public class UTResponsibleBuilderSafety {

	private static final ClassWrapper<FakeResponsible.Builder> CLASS = new ClassWrapper<>(FakeResponsible.Builder.class);
	private static final String EMPTY_STRING = "";
	private FakeResponsible.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = FakeResponsible.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("from", Responsible.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetName_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setName", String.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetName_Empty() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setName", String.class), EMPTY_STRING);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetWikipediaPage_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setWikipediaPage", URL.class), new Object[]{null});
	}
}