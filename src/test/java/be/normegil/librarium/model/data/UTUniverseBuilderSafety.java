package be.normegil.librarium.model.data;

import be.normegil.librarium.libraries.Class;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class UTUniverseBuilderSafety {

	private static final Class<Universe.Builder> CLASS = new Class<>(Universe.Builder.class);
	private static final String EMPTY_STRING = "";
	private Universe.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = Universe.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("from", Universe.class), new Object[]{null});
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
	public void testSetDescription_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setDescription", String.class), new Object[]{null});
	}

	@Test
	public void testSetDescription_Empty() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setDescription", String.class), EMPTY_STRING);
		assertEquals(EMPTY_STRING, entity.build().getDescription());
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