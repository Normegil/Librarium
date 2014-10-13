package be.normegil.librarium.rest.game;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.data.Entity;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.UriInfo;
import java.util.UUID;

public class UTGameREST_Safety {

	public static final String NAME = "Name";
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Entity> ENTITY_FACTORY = FactoryRepository.get(Entity.class);
	private static final ClassWrapper<GameREST> CLASS = new ClassWrapper<>(GameREST.class);
	private GameREST entity;

	@Mock
	private UriInfo info;

	public UTGameREST_Safety() {
		Mockito.when(info.getBaseUri()).thenReturn(URL_FACTORY.getDefault().toURI());
	}

	@Before
	public void setUp() throws Exception {
		entity = new GameREST();
	}

	@Test(expected = ConstraintViolationException.class)
	public void testGetAll_NullInfo() throws Exception {
		Validator.validate(entity, CLASS.getMethod("getAll", UriInfo.class, Long.class, Integer.class), null, 0L, ApplicationProperties.REST.DEFAULT_LIMIT);
	}

	@Test
	public void testGetAll_NullOffset() throws Exception {
		Validator.validate(entity, CLASS.getMethod("getAll", UriInfo.class, Long.class, Integer.class), info, null, 0);
	}

	@Test
	public void testGetAll_NullLimit() throws Exception {
		Validator.validate(entity, CLASS.getMethod("getAll", UriInfo.class, Long.class, Integer.class), info, 0L, null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testGet_NullInfo() throws Exception {
		Validator.validate(entity, CLASS.getMethod("get", UriInfo.class, UUID.class), null, UUID.randomUUID());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testGet_NullUUID() throws Exception {
		Validator.validate(entity, CLASS.getMethod("get", UriInfo.class, UUID.class), info, null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCreate_NullInfo() throws Exception {
		Validator.validate(entity, CLASS.getMethod("create", UriInfo.class, Game.class), null, ENTITY_FACTORY.getNew());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCreate_NullEntity() throws Exception {
		Validator.validate(entity, CLASS.getMethod("create", UriInfo.class, Game.class), info, null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testUpdateByPUT_NullID() throws Exception {
		Validator.validate(entity, CLASS.getMethod("updateByPUT", UUID.class, Entity.class), null, ENTITY_FACTORY.getNew());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testUpdateByPUT_NullEntity() throws Exception {
		Validator.validate(entity, CLASS.getMethod("updateByPUT", UUID.class, Entity.class), UUID.randomUUID(), null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testUpdateByPOST_NullInfo() throws Exception {
		Validator.validate(entity, CLASS.getMethod("updateByPOST", UriInfo.class, UUID.class, Entity.class), null, UUID.class, ENTITY_FACTORY.getNew());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testUpdateByPOST_NullID() throws Exception {
		Validator.validate(entity, CLASS.getMethod("updateByPOST", UriInfo.class, UUID.class, Entity.class), info, null, ENTITY_FACTORY.getNew());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testUpdateByPOST_NullEntity() throws Exception {
		Validator.validate(entity, CLASS.getMethod("updateByPOST", UriInfo.class, UUID.class, Entity.class), info, UUID.randomUUID(), null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testDelete_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("delete", UUID.class), new Object[]{null});
	}
}
