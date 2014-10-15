package be.normegil.librarium.util.parser;

import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

public class UTParserSafety {

	private static final ClassWrapper<Parser> CLASS = new ClassWrapper<>(Parser.class);
	private static final java.lang.reflect.Method TO_METHOD = CLASS.getMethod("to", Object.class, Parser.DocumentType.class, OutputStream.class);
	private Parser entity;

	@Before
	public void setUp() throws Exception {
		entity = new Parser<>(ParserTestSuite.ParserExample.class);
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(Object.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testTo_NullEntity() throws Exception {
		Validator.validate(entity, TO_METHOD, null, Parser.DocumentType.XML, new FileOutputStream(new File("test")));
	}

	@Test(expected = ConstraintViolationException.class)
	public void testTo_NullDocumentType() throws Exception {
		ParserTestSuite.ParserExample example = new ParserTestSuite.ParserExample(UUID.randomUUID(), "test");
		Validator.validate(entity, TO_METHOD, example, null, new FileOutputStream(new File("test")));
	}

	@Test(expected = ConstraintViolationException.class)
	public void testTo_NullStream() throws Exception {
		ParserTestSuite.ParserExample example = new ParserTestSuite.ParserExample(UUID.randomUUID(), "test");
		Validator.validate(entity, TO_METHOD, example, Parser.DocumentType.XML, null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom_NullDocumentType() throws Exception {
		Validator.validate(entity, TO_METHOD, null, new FileInputStream(new File("test")));
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom_NullStream() throws Exception {
		Validator.validate(entity, TO_METHOD, Parser.DocumentType.XML, null);
	}
}