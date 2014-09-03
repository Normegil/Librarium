package be.normegil.librarium.model.data.game;

import be.normegil.librarium.libraries.Class;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.Collection;

public class UTGameSerieBuilderSafety {

	private static final Class<GameSerie.Builder> CLASS = new Class<>(GameSerie.Builder.class);
	private GameSerie.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = GameSerie.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("from", GameSerie.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testEmptyBuildReturnValue() throws Exception {
		Validator.validate(entity, CLASS.getMethod("build"));
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllGames_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllGames", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddGame_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addGame", Game.class), new Object[]{null});
	}
}