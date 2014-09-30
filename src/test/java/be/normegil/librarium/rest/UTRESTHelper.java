package be.normegil.librarium.rest;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.dao.DAO;
import be.normegil.librarium.model.dao.MemoryTestDAO;
import be.normegil.librarium.model.data.Entity;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ContextResolver;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.net.URI;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UTRESTHelper {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> GAME_FACTORY = FactoryRepository.get(Game.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private RESTServiceHelper<Game> restServiceHelper;
	private DAO<Game> dao;
	private URI baseURI;

	@Mock
	private ContextResolver<Marshaller> context;

	@Mock
	private Updater<Game> updater;

	@Mock
	private UriInfo info;

	@Before
	public void setUp() throws Exception {
		dao = new MemoryTestDAO();
		restServiceHelper = new RESTServiceHelper<>(dao, context, updater);

		baseURI = URL_FACTORY.getNext().toURI();
		when(info.getBaseUri())
				.thenReturn(baseURI);

		JAXBContext jaxbContext = JAXBContext.newInstance();
		when(context.getContext(restServiceHelper.getClass()))
				.thenReturn(jaxbContext.createMarshaller());
	}

	@After
	public void tearDown() throws Exception {
		restServiceHelper = null;
		dao = null;
	}

	@Test
	public void testGet() throws Exception {
		Entity entity = dao.getAll().iterator().next();
		Response response = restServiceHelper.get(info, entity.getId());
		Entity entityFromResponse = (Entity) response.getEntity();
		assertEquals(entity, entityFromResponse);
	}

	@Test
	public void testGet_IDNotFound() throws Exception {
		Response response = restServiceHelper.get(info, UUID.randomUUID());
		Entity entityFromResponse = (Entity) response.getEntity();
		assertNull(entityFromResponse);
	}

	@Test
	public void testGet_IDNotFound_ResponseStatus() throws Exception {
		Response response = restServiceHelper.get(info, UUID.randomUUID());
		assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatus());
	}

	@Test
	public void testCreate_ResponseStatus() throws Exception {
		Game entity = GAME_FACTORY.getNext();
		Response response = restServiceHelper.create(info, entity);
		assertEquals(HttpStatus.SC_OK, response.getStatus());
	}

	@Test
	public void testCreate_LocationValid() throws Exception {
		Game entity = GAME_FACTORY.getNext();
		Response response = restServiceHelper.create(info, entity);
		URI location = response.getLocation();
		URL baseURL = new URL(info.getBaseUri());
		URL urlToEntity = baseURL.addToPath(Constants.URL.PATH_SEPARATOR + entity.getId());
		assertEquals(urlToEntity.toURI(), location);
	}

	@Test
	public void testCreate_WithID() throws Exception {
		Game entity = GAME_FACTORY.getNext();
		new EntityHelper().setId(entity, UUID.randomUUID());
		Response response = restServiceHelper.create(info, entity);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatus());
	}

	@Test
	public void testUpdateByPUT_Status() throws Exception {
		Game entity = dao.getAll().iterator().next();
		Game newEntity = GAME_FACTORY.getNext();
		new EntityHelper().setId(newEntity, entity.getId());
		Response response = restServiceHelper.updateByPUT(entity.getId(), entity);
		assertEquals(HttpStatus.SC_OK, response.getStatus());
	}

	@Test
	public void testUpdateByPUT_UpdaterCalled() throws Exception {
		Game entity = dao.getAll().iterator().next();
		Game newEntity = GAME_FACTORY.getNext();
		new EntityHelper().setId(newEntity, entity.getId());
		restServiceHelper.updateByPUT(entity.getId(), newEntity);
		verify(updater, times(1))
				.updateEverything(entity, newEntity);
	}

	@Test
	public void testUpdateByPUT_NotExisting() throws Exception {
		UUID uuid = UUID.randomUUID();
		Game newEntity = GAME_FACTORY.getNext();
		new EntityHelper().setId(newEntity, uuid);
		Response response = restServiceHelper.updateByPUT(uuid, newEntity);
		assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatus());
	}

	@Test
	public void testUpdateByPUT_EntityIDNull_Status() throws Exception {
		Game entity = dao.getAll().iterator().next();
		Game newEntity = GAME_FACTORY.getNext();
		Response response = restServiceHelper.updateByPUT(entity.getId(), newEntity);
		assertEquals(HttpStatus.SC_OK, response.getStatus());
	}

	@Test
	public void testUpdateByPUT_EntityIDNull_UpdaterCalled() throws Exception {
		Game entity = dao.getAll().iterator().next();
		Game newEntity = GAME_FACTORY.getNext();
		restServiceHelper.updateByPUT(entity.getId(), newEntity);
		verify(updater, times(1))
				.updateEverything(entity, newEntity);
	}

	@Test
	public void testUpdateByPUT_DifferentsIDs() throws Exception {
		Game entity = dao.getAll().iterator().next();
		Game newEntity = GAME_FACTORY.getNext();
		new EntityHelper().setId(newEntity, UUID.randomUUID());
		Response response = restServiceHelper.updateByPUT(entity.getId(), newEntity);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatus());
	}

	@Test
	public void testUpdateByPOST_UpdaterCalled() throws Exception {
		Game entity = dao.getAll().iterator().next();
		Game newEntity = GAME_FACTORY.getNext();
		new EntityHelper().setId(newEntity, entity.getId());
		restServiceHelper.updateByPOST(info, entity.getId(), newEntity);
		verify(updater, times(1))
				.updateNotNull(entity, newEntity);
	}

	@Test
	public void testUpdateByPOST_Status() throws Exception {
		Game entity = dao.getAll().iterator().next();
		Game newEntity = GAME_FACTORY.getNext();
		new EntityHelper().setId(newEntity, entity.getId());
		Response response = restServiceHelper.updateByPOST(info, entity.getId(), newEntity);
		assertEquals(HttpStatus.SC_OK, response.getStatus());
	}

	@Test
	public void testUpdateByPOST_NotExisting() throws Exception {
		UUID uuid = UUID.randomUUID();
		Game newEntity = GAME_FACTORY.getNext();
		new EntityHelper().setId(newEntity, uuid);
		Response response = restServiceHelper.updateByPOST(info, uuid, newEntity);
		assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatus());
	}

	@Test
	public void testUpdateByPOST_EntityIDNull_UpdaterCalled() throws Exception {
		Game entity = dao.getAll().iterator().next();
		Game newEntity = GAME_FACTORY.getNext();
		restServiceHelper.updateByPOST(info, entity.getId(), newEntity);
		verify(updater, times(1))
				.updateNotNull(entity, newEntity);
	}

	@Test
	public void testUpdateByPOST_EntityIDNull_Status() throws Exception {
		Game entity = dao.getAll().iterator().next();
		Game newEntity = GAME_FACTORY.getNext();
		Response response = restServiceHelper.updateByPOST(info, entity.getId(), newEntity);
		assertEquals(HttpStatus.SC_OK, response.getStatus());
	}

	@Test
	public void testDelete_Status() throws Exception {
		Entity entity = dao.getAll().iterator().next();
		Response response = restServiceHelper.delete(entity.getId());
		assertEquals(HttpStatus.SC_OK, response.getStatus());
	}

	@Test
	public void testDelete() throws Exception {
		Entity entity = dao.getAll().iterator().next();
		restServiceHelper.delete(entity.getId());
		assertNull(dao.get(entity.getId()));
	}

	@Test
	public void testDelete_IDNotFound() throws Exception {
		Response response = restServiceHelper.delete(UUID.randomUUID());
		assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatus());
	}

}
