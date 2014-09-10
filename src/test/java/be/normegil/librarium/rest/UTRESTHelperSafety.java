package be.normegil.librarium.rest;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.Class;
import be.normegil.librarium.model.dao.DAO;
import be.normegil.librarium.model.dao.GameTestDAO;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ContextResolver;
import javax.xml.bind.Marshaller;
import java.lang.reflect.Method;
import java.util.UUID;

public class UTRESTHelperSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> GAME_FACTORY = FactoryRepository.get(Game.class);
	private static final Class<RESTHelper> CLASS = new Class<>(RESTHelper.class);
	private static final long DEFAULT_OFFSET = 0L;
	private static final int DEFAULT_LIMIT = ApplicationProperties.REST.DEFAULT_LIMIT;
	private RESTHelper entity;
	private DAO<Game> dao;

	@Mock
	private ContextResolver<Marshaller> context;

	@Mock
	private Updater<Game> updater;

	@Mock
	private UriInfo info;

	@Before
	public void setUp() throws Exception {
		dao = new GameTestDAO();
		entity = new RESTHelper<>(dao, context, updater);
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
		dao = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testGetAll_NullInfo() throws Exception {
		Validator.validate(entity, getGetAllMethod(), null, DEFAULT_OFFSET, DEFAULT_LIMIT);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testGetAll_NullOffset() throws Exception {
		Validator.validate(entity, getGetAllMethod(), null, null, DEFAULT_LIMIT);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testGetAll_NullLimit() throws Exception {
		Validator.validate(entity, getGetAllMethod(), info, DEFAULT_OFFSET, null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testGet_NullInfo() throws Exception {
		Validator.validate(entity, getGetMethod(), null, UUID.randomUUID());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testGet_NullUUID() throws Exception {
		Validator.validate(entity, getGetMethod(), info, null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCreate_NullInfo() throws Exception {
		Validator.validate(entity, getCreateMethod(), null, GAME_FACTORY.getNext());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCreate_NullEntity() throws Exception {
		Validator.validate(entity, getCreateMethod(), info, null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testUpdateByPUT_NullUUID() throws Exception {
		Validator.validate(entity, getUpdateByPUTMethod(), null, GAME_FACTORY.getNext());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testUpdateByPUT_NullEntity() throws Exception {
		Validator.validate(entity, getUpdateByPUTMethod(), UUID.randomUUID(), null);
	}



	private Method getUpdateByPUTMethod() {
		return CLASS.getMethod("updateByPUT", UUID.class, Object.class);
	}

	private Method getCreateMethod() {
		return CLASS.getMethod("create", UriInfo.class, Object.class);
	}

	private Method getGetAllMethod() {
		return CLASS.getMethod("getAll", UriInfo.class, Long.class, Integer.class);
	}

	private Method getGetMethod() {
		return CLASS.getMethod("get", UriInfo.class, UUID.class);
	}
}