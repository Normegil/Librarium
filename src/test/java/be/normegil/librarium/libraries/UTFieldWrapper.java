package be.normegil.librarium.libraries;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.Entity;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UTFieldWrapper {

	public static final String FIELD_NAME = "id";
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> GAME_FACTORY = FactoryRepository.get(Game.class);
	private FieldWrapper entity;

	@Before
	public void setUp() throws Exception {
		ClassWrapper<Entity> wrapper = new ClassWrapper<>(Entity.class);
		entity = wrapper.getField(FIELD_NAME);
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testGetOriginalField() throws Exception {
		Field expected = Entity.class.getDeclaredField(FIELD_NAME);
		FieldWrapper wrapper = new FieldWrapper(expected);
		assertEquals(expected, wrapper.getOriginalField());
	}

	@Test
	public void testGetName() throws Exception {
		Field field = Entity.class.getDeclaredField(FIELD_NAME);
		FieldWrapper wrapper = new FieldWrapper(field);
		assertEquals(wrapper.getName(), field.getName());
	}

	@Test
	public void testSet_Null() throws Exception {
		Entity game = GAME_FACTORY.getNew();
		new EntityHelper().setId(game, UUID.randomUUID());
		entity.set(game, null);
		assertNull(game.getId());
	}

	@Test
	public void testSet() throws Exception {
		Entity game = GAME_FACTORY.getNew();
		UUID expected = UUID.randomUUID();
		entity.set(game, expected);
		assertEquals(expected, game.getId());
	}
}
