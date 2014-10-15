package be.normegil.librarium.util.parser;

import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UTCsvParserSafety {

	private static final ClassWrapper<CsvParser> CLASS = new ClassWrapper<>(CsvParser.class);
	private CsvParser entity;

	@Before
	public void setUp() throws Exception {
		entity = new CsvParser<>(CsvParserTestSuite.CsvParserExample.class);
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
	public void testConstructor_NotSchema() throws Exception {
		Validator.validate(CLASS.getConstructor(Class.class), Game.class);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("from", File.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testTo_NullList() throws Exception {
		Validator.validate(entity, CLASS.getMethod("to", List.class, File.class), null, new File("Temp"));
	}

	@Test(expected = ConstraintViolationException.class)
	public void testTo_NullFile() throws Exception {
		Validator.validate(entity, CLASS.getMethod("to", List.class, File.class), new ArrayList<>(), null);
	}
}