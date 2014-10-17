package be.normegil.librarium.model.rest.services;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.model.dao.DAO;
import be.normegil.librarium.model.data.Entity;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ContextResolver;
import javax.xml.bind.Marshaller;
import java.lang.reflect.Method;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class UTBasicRESTServiceSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> FACTORY = FactoryRepository.get(Game.class);
	private static final ClassWrapper<BasicRESTService> CLASS = new ClassWrapper<>(BasicRESTService.class);
	private static final Method UPDATE_BY_PUT_METHOD = CLASS.getMethod("updateByPUT", UUID.class, Object.class);
	private static final Method CREATE_METHOD = CLASS.getMethod("create", UriInfo.class, Object.class);
	private static final Method GET_METHOD = CLASS.getMethod("get", UriInfo.class, UUID.class);
	private static final Method GET_ALL_METHOD = CLASS.getMethod("getAll", UriInfo.class, Long.class, Integer.class);
	private static final Method UPDATE_BY_POST_METHOD = CLASS.getMethod("updateByPOST", UriInfo.class, UUID.class, Object.class);
	private static final Method DELETE_METHOD = CLASS.getMethod("delete", UUID.class);
	private BasicRESTService entity = new BasicRESTService() {
		@Override
		protected void update(final Entity loadedEntity, final Entity entity) {
		}

		@Override
		protected void updateNullCheck(final Entity loadedEntity, final Entity entity) {

		}

		@Override
		public DAO getDao() {
			return null;
		}

		@Override
		public void setDAO(final DAO dao) {

		}

		@Override
		public Logger getLogger() {
			return null;
		}

		@Override
		public void setLogger(final Logger log) {

		}

		@Override
		public ContextResolver<Marshaller> getContextResolver() {
			return null;
		}

		@Override
		public void setContextResolver(final ContextResolver contextResolver) {

		}

		@Override
		public Class getSupportedClass() {
			return null;
		}
	};

	private UriInfo uriInfo;

	@Test(expected = ConstraintViolationException.class)
	public void testGetAll_NullUriInfo() throws Exception {
		Validator.validate(entity, GET_ALL_METHOD, null, 1L, 5);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testGet_NullUriInfo() throws Exception {
		Validator.validate(entity, GET_METHOD, null, UUID.randomUUID());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testGet_NullUUID() throws Exception {
		Validator.validate(entity, GET_METHOD, uriInfo, null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCreate_NullUriInfo() throws Exception {
		Validator.validate(entity, CREATE_METHOD, null, FACTORY.getNew());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCreate_NullObject() throws Exception {
		Validator.validate(entity, CREATE_METHOD, uriInfo, null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCreate_ObjectWithID() throws Exception {
		Validator.validate(entity, CREATE_METHOD, uriInfo, FACTORY.getNew(true, true));
	}

	@Test
	public void testCreate_InvalidObject() throws Exception {
		Game game = FACTORY.getNew(true, true);
		game.setTitle(null);
		Validator.validate(entity, CREATE_METHOD, uriInfo, game);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testUpdateByPUT_NullUUID() throws Exception {
		Validator.validate(entity, UPDATE_BY_PUT_METHOD, null, FACTORY.getNew());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testUpdateByPUT_NullObject() throws Exception {
		Validator.validate(entity, UPDATE_BY_PUT_METHOD, UUID.randomUUID(), null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testUpdateByPUT_DifferentIDGivenAndInObject() throws Exception {
		Validator.validate(entity, UPDATE_BY_PUT_METHOD, UUID.randomUUID(), FACTORY.getNew());
	}

	@Test
	public void testUpdateByPut_InvalidObject() throws Exception {
		Game game = FACTORY.getNew(true, true);
		game.setTitle(null);
		Validator.validate(entity, UPDATE_BY_PUT_METHOD, UUID.randomUUID(), game);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testUpdateByPOST_NullUriInfo() throws Exception {
		Validator.validate(entity, UPDATE_BY_POST_METHOD, null, UUID.randomUUID(), FACTORY.getNew());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testUpdateByPOST_NullUUID() throws Exception {
		Validator.validate(entity, UPDATE_BY_POST_METHOD, uriInfo, null, FACTORY.getNew());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testUpdateByPOST_NullObject() throws Exception {
		Validator.validate(entity, UPDATE_BY_POST_METHOD, uriInfo, UUID.randomUUID(), null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testUpdateByPOST_DifferentIDGivenAndInObject() throws Exception {
		Validator.validate(entity, UPDATE_BY_POST_METHOD, uriInfo, UUID.randomUUID(), FACTORY.getNew());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testDelete_Null() throws Exception {
		Validator.validate(entity, DELETE_METHOD, new Object[]{null});
	}
}
