package be.normegil.librarium.rest;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.dao.DAO;
import be.normegil.librarium.tool.MemoryTestDAO;
import be.normegil.librarium.model.data.Entity;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.model.rest.CollectionResource;
import be.normegil.librarium.tool.DataFactory;
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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UTRESTHelperGetAll {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private static final long DEFAULT_OFFSET = 2L;
	private static final int DEFAULT_LIMIT = 6;
	private static final int NUMBER_OF_GAMES = DEFAULT_LIMIT * 2;
	private static final long FIRST_OFFSET = 0L;
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
		dao = new MemoryTestDAO<>(NUMBER_OF_GAMES, Game.class);
		restServiceHelper = new RESTServiceHelper<>(dao, context, updater);

		baseURI = URL_FACTORY.getNew().toURI();
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
	public void testGetAll_Items() throws Exception {
		Response response = restServiceHelper.getAll(info, DEFAULT_OFFSET, DEFAULT_LIMIT);
		CollectionResource resource = (CollectionResource) response.getEntity();
		List<Game> games = dao.getAll(DEFAULT_OFFSET, DEFAULT_LIMIT);
		URL url = new URL(baseURI.toURL());
		List<URL> expected = Entity.helper().convertToURLs(games, url);
		assertEquals(expected, resource.getItems());
	}

	@Test
	public void testGetAll_Offset() throws Exception {
		Response response = restServiceHelper.getAll(info, DEFAULT_OFFSET, DEFAULT_LIMIT);
		CollectionResource resource = (CollectionResource) response.getEntity();
		assertEquals((Long) DEFAULT_OFFSET, resource.getOffset());
	}

	@Test
	public void testGetAll_Limit() throws Exception {
		Response response = restServiceHelper.getAll(info, DEFAULT_OFFSET, DEFAULT_LIMIT);
		CollectionResource resource = (CollectionResource) response.getEntity();
		assertEquals(DEFAULT_LIMIT, resource.getLimit());
	}

	@Test
	public void testGetAll_TotalNumberOfItems() throws Exception {
		Response response = restServiceHelper.getAll(info, DEFAULT_OFFSET, DEFAULT_LIMIT);
		CollectionResource resource = (CollectionResource) response.getEntity();
		assertEquals(dao.getNumberOfElements(), resource.getTotalNumberOfItems());
	}

	@Test
	public void testGetAll_FirstLink() throws Exception {
		Response response = restServiceHelper.getAll(info, DEFAULT_OFFSET, DEFAULT_LIMIT);
		CollectionResource resource = (CollectionResource) response.getEntity();
		URL url = new URL(baseURI.toURL());
		URL link = CollectionResource.helper().getCollectionURL(url, FIRST_OFFSET, DEFAULT_LIMIT);
		assertEquals(link, resource.getURLToFirstPage());
	}

	@Test
	public void testGetAll_PreviousLink() throws Exception {
		Response response = restServiceHelper.getAll(info, DEFAULT_OFFSET, DEFAULT_LIMIT);
		CollectionResource resource = (CollectionResource) response.getEntity();
		URL url = new URL(baseURI.toURL());
		long previousOffset = getPreviousOffset(DEFAULT_OFFSET, DEFAULT_LIMIT);
		URL link = CollectionResource.helper().getCollectionURL(url, previousOffset, DEFAULT_LIMIT);
		assertEquals(link, resource.getURLToPreviousPage());
	}

	private long getPreviousOffset(final long actualOffset, final int limit) {
		long offset = actualOffset - limit;
		return offset > 0 ? offset : 0;
	}

	@Test
	public void testGetAll_NextLink() throws Exception {
		Response response = restServiceHelper.getAll(info, DEFAULT_OFFSET, DEFAULT_LIMIT);
		CollectionResource resource = (CollectionResource) response.getEntity();
		URL url = new URL(baseURI.toURL());
		URL link = CollectionResource.helper().getCollectionURL(url, DEFAULT_OFFSET + DEFAULT_LIMIT, DEFAULT_LIMIT);
		assertEquals(link, resource.getURLToNextPage());
	}

	@Test
	public void testGetAll_LastLink() throws Exception {
		Response response = restServiceHelper.getAll(info, DEFAULT_OFFSET, DEFAULT_LIMIT);
		CollectionResource resource = (CollectionResource) response.getEntity();
		URL url = new URL(baseURI.toURL());
		long numberOfElements = dao.getNumberOfElements();
		long numberOfPagesMinusOne = numberOfElements / DEFAULT_LIMIT;
		if (numberOfElements % DEFAULT_LIMIT == 0) {
			numberOfPagesMinusOne -= 1;
		}
		long lastOffset = numberOfPagesMinusOne * DEFAULT_LIMIT;
		URL link = CollectionResource.helper().getCollectionURL(url, lastOffset, DEFAULT_LIMIT);
		assertEquals(link, resource.getURLToLastPage());
	}

	@Test
	public void testGetAll_ResponseStatus() throws Exception {
		Response response = restServiceHelper.getAll(info, DEFAULT_OFFSET, DEFAULT_LIMIT);
		assertEquals(response.getStatus(), HttpStatus.SC_OK);
	}
}
