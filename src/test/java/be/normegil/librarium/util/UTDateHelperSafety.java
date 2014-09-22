package be.normegil.librarium.util;

import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

public class UTDateHelperSafety {

	private static final ClassWrapper<DateHelper> CLASS = new ClassWrapper<>(DateHelper.class);
	private static final String EMPTY_STRING = "";
	private static final String WRONG_FORMAT = "06/07/2014";
	private DateHelper entity;

	@Before
	public void setUp() throws Exception {
		entity = new DateHelper();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFormat_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("format", LocalDate.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testParse_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("parseLocalDate", String.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testParse_Empty() throws Exception {
		Validator.validate(entity, CLASS.getMethod("parseLocalDate", String.class), EMPTY_STRING);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testParse_WrongFormat() throws Exception {
		Validator.validate(entity, CLASS.getMethod("parseLocalDate", String.class), WRONG_FORMAT);
	}
}