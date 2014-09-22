package be.normegil.librarium.model.data;

import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.Collection;

public class UTSupportBuilderSafety {

	private static final ClassWrapper<Support.Builder> CLASS = new ClassWrapper<>(Support.Builder.class);
	private static final String EMPTY_STRING = "";
	private Support.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = Support.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("from", Support.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testEmptyBuildReturnValue() throws Exception {
		Validator.validate(entity, CLASS.getMethod("build"));
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

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllMedias_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllMedias", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddMedia_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addMedia", Media.class), new Object[]{null});
	}
}