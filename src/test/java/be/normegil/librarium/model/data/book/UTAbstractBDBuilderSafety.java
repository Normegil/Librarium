package be.normegil.librarium.model.data.book;

import be.normegil.librarium.libraries.Class;
import be.normegil.librarium.model.data.fake.FakeAbstractBD;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;

public class UTAbstractBDBuilderSafety {

	private static final Class<FakeAbstractBD.Builder> CLASS = new Class<>(FakeAbstractBD.Builder.class);
	private FakeAbstractBD.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = FakeAbstractBD.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("from", AbstractBD.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetIssueNumber_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setIssueNumber", Long.class), new Object[]{null});
	}
}