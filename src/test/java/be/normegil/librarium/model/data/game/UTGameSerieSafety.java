package be.normegil.librarium.model.data.game;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.Class;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.Collection;

public class UTGameSerieSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<GameSerie> FACTORY = FactoryRepository.get(GameSerie.class);
	private static final Class<GameSerie> CLASS = new Class<>(GameSerie.class);
	private GameSerie entity;

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
		Validator.validate(CLASS.getConstructor(GameSerie.Init.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCopyConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(GameSerie.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllGames_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllGames", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddGame_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addGame", Game.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveAllGames_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeAllGames", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveGame_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeGame", Game.class), new Object[]{null});
	}
}