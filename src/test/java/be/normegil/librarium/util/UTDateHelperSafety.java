package be.normegil.librarium.util;

import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UTDateHelperSafety {

	private static final ClassWrapper<DateHelper> CLASS = new ClassWrapper<>(DateHelper.class);
	private static final Method PARSE_LOCAL_DATE_METHOD = CLASS.getMethod("parseLocalDate", String.class);
	private static final Method PARSE_LOCAL_DATE_TIME_METHOD = CLASS.getMethod("parseLocalDateTime", String.class);
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
	public void testFormatLocalDate_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("format", LocalDate.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFormatLocalDateTime_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("format", LocalDateTime.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testParseLocalDate_Null() throws Exception {
		Validator.validate(entity, PARSE_LOCAL_DATE_METHOD, new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testParseLocalDate_Empty() throws Exception {
		Validator.validate(entity, PARSE_LOCAL_DATE_METHOD, EMPTY_STRING);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testParseLocalDate_WrongFormat() throws Exception {
		Validator.validate(entity, PARSE_LOCAL_DATE_METHOD, WRONG_FORMAT);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testParseLocalDateTime_Null() throws Exception {
		Validator.validate(entity, PARSE_LOCAL_DATE_TIME_METHOD, new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testParseLocalDateTime_Empty() throws Exception {
		Validator.validate(entity, PARSE_LOCAL_DATE_TIME_METHOD, EMPTY_STRING);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testParseLocalDateTime_WrongFormat() throws Exception {
		Validator.validate(entity, PARSE_LOCAL_DATE_TIME_METHOD, WRONG_FORMAT);
	}
}