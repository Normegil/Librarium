package be.normegil.librarium.libraries;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.Entity;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Field;

public class UTFieldWrapperSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> GAME_FACTORY = FactoryRepository.get(Game.class);
	private static final ClassWrapper<FieldWrapper> CLASS = new ClassWrapper<>(FieldWrapper.class);
	private FieldWrapper entity;

	@Before
	public void setUp() throws Exception {
		ClassWrapper<Entity> wrapper = new ClassWrapper<>(Entity.class);
		entity = wrapper.getField("id");
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCopyConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(Field.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSet_NullEntity() throws Exception {
		Validator.validate(entity, CLASS.getMethod("set", Object.class, Object.class), null, 1L);
	}
}