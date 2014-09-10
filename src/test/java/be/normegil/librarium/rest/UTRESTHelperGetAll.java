package be.normegil.librarium.rest;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.dao.DAO;
import be.normegil.librarium.model.dao.GameTestDAO;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.model.rest.CollectionResource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ContextResolver;
import javax.xml.bind.Marshaller;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class UTRESTHelperGetAll {
	private static final long FIRST_OFFSET = 0L;
	private static final int DEFAULT_LIMIT = ApplicationProperties.REST.DEFAULT_LIMIT;
	private RESTHelper<Game> restHelper;
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
		restHelper = new RESTHelper<>(dao, context, updater);
	}

	@After
	public void tearDown() throws Exception {
		restHelper = null;
		dao = null;
	}

	@Test
	public void testGetAll() throws Exception {
		Response response = restHelper.getAll(info, null, null);
		CollectionResource resource = (CollectionResource) response.getEntity();
		List<Game> daoGames = dao.getAll();
		URL baseURL = new URL(info.getBaseUri());
		List<URL> urlsToDAOGames = new RESTCollectionHelper().convertToURLs(daoGames, baseURL);
		assertEquals(urlsToDAOGames, resource.getItems());
	}

	@Test
	public void testGetAll_DefaultOffset() throws Exception {
		Response response = restHelper.getAll(info, null, null);
		CollectionResource resource = (CollectionResource) response.getEntity();
		assertEquals((Long) FIRST_OFFSET, resource.getOffset());
	}

	@Test
	public void testGetAll_DefaultLimit() throws Exception {
		Response response = restHelper.getAll(info, null, null);
		CollectionResource resource = (CollectionResource) response.getEntity();
		assertEquals(ApplicationProperties.REST.DEFAULT_LIMIT, resource.getLimit());
	}

	@Test
	public void testGetAll_LinkToFirstPage() throws Exception {
		Response response = restHelper.getAll(info, null, null);
		CollectionResource resource = (CollectionResource) response.getEntity();
		URL baseURL = new URL(info.getBaseUri());
		assertEquals(baseURL.addToPath("?offset=0"), resource.getURLToFirstPage());
	}

	@Test
	public void testGetAll_LinkToPreviousPage() throws Exception {
		Response response = restHelper.getAll(info, null, null);
		CollectionResource resource = (CollectionResource) response.getEntity();
		assertNull(resource.getURLToPreviousPage());
	}

	@Test
	public void testGetAll_LinkToNextPage() throws Exception {
		Response response = restHelper.getAll(info, null, null);
		CollectionResource resource = (CollectionResource) response.getEntity();
		URL baseURL = new URL(info.getBaseUri());
		assertEquals(baseURL.addToPath("?offset=" + DEFAULT_LIMIT), resource.getURLToNextPage());
	}

	@Test
	public void testGetAll_LinkToLastPage() throws Exception {
		Response response = restHelper.getAll(info, null, null);
		CollectionResource resource = (CollectionResource) response.getEntity();
		URL baseURL = new URL(info.getBaseUri());
		long lastOffset = dao.getNumberOfElements() / DEFAULT_LIMIT;
		assertEquals(baseURL.addToPath("?offset=" + lastOffset), resource.getURLToNextPage());
	}

	@Test
	public void testGetAll_Empty() throws Exception {
		clearDAO();

		Response response = restHelper.getAll(info, null, null);
		CollectionResource resource = (CollectionResource) response.getEntity();
		assertTrue(resource.getItems().isEmpty());
	}

	@Test
	public void testGetAll_Empty_Offset() throws Exception {
		clearDAO();

		Response response = restHelper.getAll(info, null, null);
		CollectionResource resource = (CollectionResource) response.getEntity();
		assertEquals((Long) FIRST_OFFSET, resource.getOffset());
	}

	@Test
	public void testGetAll_Empty_Limit() throws Exception {
		clearDAO();

		Response response = restHelper.getAll(info, null, null);
		CollectionResource resource = (CollectionResource) response.getEntity();
		assertEquals(DEFAULT_LIMIT, resource.getLimit());
	}

	@Test
	public void testGetAll_Empty_URLToFirstPage() throws Exception {
		clearDAO();

		Response response = restHelper.getAll(info, null, null);
		CollectionResource resource = (CollectionResource) response.getEntity();
		URL baseURL = new URL(info.getBaseUri());
		assertEquals(baseURL.addToPath("?offset=" + FIRST_OFFSET), resource.getLimit());
	}

	@Test
	public void testGetAll_Empty_URLToNextPage() throws Exception {
		clearDAO();

		Response response = restHelper.getAll(info, null, null);
		CollectionResource resource = (CollectionResource) response.getEntity();
		URL baseURL = new URL(info.getBaseUri());
		assertEquals(baseURL.addToPath("?offset=" + ), resource.getLimit());
	}

	@Test
	public void testGetAll_GivenOffset_NullLimit() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testGetAll_NullOffset_GivenLimit() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testGetAll_GivenOffset_GivenLimit() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testGetAll_NegativeOffset() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testGetAll_OffsetTooHigh() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testGetAll_LimitTooHigh() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testGetAll_NegativeLimit() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testGetAll_LimitZero() throws Exception {
		throw new NotImplementedException();
	}

	private void clearDAO() {
		List<Game> entities = dao.getAll(FIRST_OFFSET, (int) dao.getNumberOfElements());
		for (Game entity : entities) {
			dao.remove(entity);
		}
	}
}
