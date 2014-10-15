package be.normegil.librarium.util.parser;

import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

public class UTJacksonParserSafety {

	private static final ClassWrapper<JacksonParser> CLASS = new ClassWrapper<>(JacksonParser.class);
	private JacksonParser entity;

	@Before
	public void setUp() throws Exception {
		entity = new JacksonParser<>(JacksonParserTestSuite.JacksonParserExample.class);
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(Class.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("from", InputStream.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testTo_NullList() throws Exception {
		Validator.validate(entity, CLASS.getMethod("to", Object.class, File.class), null, new File("test"));
	}

	@Test(expected = ConstraintViolationException.class)
	public void testTo_NullFile() throws Exception {
		Validator.validate(entity, CLASS.getMethod("to", Object.class, File.class), new ArrayList<>(), null);
	}
}