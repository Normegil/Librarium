package be.normegil.librarium.model.data.game;

import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.Collection;

public class UTGameBuilderSafety {

	private static final ClassWrapper<Game.Builder> CLASS = new ClassWrapper<>(Game.Builder.class);
	private Game.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = Game.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("from", Game.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testEmptyBuildReturnValue() throws Exception {
		Validator.validate(entity, CLASS.getMethod("build"));
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllDevelopers_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllDevelopers", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddDeveloper_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addDeveloper", Responsible.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllEditors_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllEditors", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddEditor_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addEditor", Responsible.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllComposers_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllComposers", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddComposer_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addComposer", Responsible.class), new Object[]{null});
	}
}