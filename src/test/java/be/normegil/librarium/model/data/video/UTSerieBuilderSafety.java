package be.normegil.librarium.model.data.video;

import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.Collection;

public class UTSerieBuilderSafety {

	private static final ClassWrapper<Serie.Builder> CLASS = new ClassWrapper<>(Serie.Builder.class);
	private Serie.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = Serie.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("from", Serie.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testEmptyBuildReturnValue() throws Exception {
		Validator.validate(entity, CLASS.getMethod("build"));
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllSeasons_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllSeasons", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddSeason_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addSeason", SerieSeason.class), new Object[]{null});
	}
}