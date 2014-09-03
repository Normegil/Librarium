package be.normegil.librarium.model.data.game;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.Class;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.Collection;

public class UTGameSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> FACTORY = FactoryRepository.get(Game.class);
	private static final Class<Game> CLASS = new Class<>(Game.class);
	private Game entity;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getNew();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testBuilderConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(Game.Init.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCopyConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(Game.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetSerie_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setSerie", GameSerie.class), new Object[]{null});
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
	public void testRemoveAllDevelopers_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeAllDevelopers", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveDeveloper_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeDeveloper", Responsible.class), new Object[]{null});
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
	public void testRemoveAllEditors_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeAllEditors", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveEditor_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeEditor", Responsible.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllComposers_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllComposers", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddComposer_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addComposer", Responsible.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveAllComposers_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeAllComposers", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveComposer_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeComposer", Responsible.class), new Object[]{null});
	}
}