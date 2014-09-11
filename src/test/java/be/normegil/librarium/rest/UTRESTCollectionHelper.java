package be.normegil.librarium.rest;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.dao.DAO;
import be.normegil.librarium.model.dao.GameTestDAO;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.model.rest.CollectionResource;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UTRESTCollectionHelper {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private static final int DEFAULT_LIMIT = ApplicationProperties.REST.DEFAULT_LIMIT;
	private static final long DEFAULT_OFFSET = 26L;
	private static final long FIRST_OFFSET = 0L;
	private DAO<Game> dao;
	private RESTCollectionHelper restCollectionHelper;

	@Before
	public void setUp() throws Exception {
		dao = new GameTestDAO(DEFAULT_LIMIT * 3);
		restCollectionHelper = new RESTCollectionHelper();
	}

	@After
	public void tearDown() throws Exception {
		restCollectionHelper = null;
		dao = null;
	}

	@Test
	public void testGetCollectionResource_Items() throws Exception {
		List<Game> games = dao.getAll(DEFAULT_OFFSET, DEFAULT_LIMIT);
		URL baseURL = URL_FACTORY.getNext();
		CollectionResource resource = restCollectionHelper.getCollectionResource(games, baseURL, dao.getNumberOfElements(), DEFAULT_OFFSET, DEFAULT_LIMIT);
		List<URL> gameURLs = restCollectionHelper.convertToURLs(games, baseURL);
		assertEquals(gameURLs, resource.getItems());
	}

	@Test
	public void testGetCollectionResource_Limit() throws Exception {
		List<Game> games = dao.getAll(DEFAULT_OFFSET, DEFAULT_LIMIT);
		CollectionResource resource = restCollectionHelper.getCollectionResource(games, URL_FACTORY.getNext(), dao.getNumberOfElements(), DEFAULT_OFFSET, DEFAULT_LIMIT);
		assertEquals(DEFAULT_LIMIT, resource.getLimit());
	}

	@Test
	public void testGetCollectionResource_Offset() throws Exception {
		List<Game> games = dao.getAll(DEFAULT_OFFSET, DEFAULT_LIMIT);
		CollectionResource resource = restCollectionHelper.getCollectionResource(games, URL_FACTORY.getNext(), dao.getNumberOfElements(), DEFAULT_OFFSET, DEFAULT_LIMIT);
		assertEquals((Long) DEFAULT_OFFSET, resource.getOffset());
	}

	@Test
	public void testGetCollectionResource_URLToFirstPage() throws Exception {
		List<Game> games = dao.getAll(DEFAULT_OFFSET, DEFAULT_LIMIT);
		URL baseURL = URL_FACTORY.getNext();
		CollectionResource resource = restCollectionHelper.getCollectionResource(games, baseURL, dao.getNumberOfElements(), DEFAULT_OFFSET, DEFAULT_LIMIT);
		URL expected = restCollectionHelper.getCollectionURL(baseURL, FIRST_OFFSET, DEFAULT_LIMIT);
		assertEquals(expected, resource.getURLToFirstPage());
	}

	@Test
	public void testGetCollectionResource_URLToPreviousPage() throws Exception {
		List<Game> games = dao.getAll(DEFAULT_OFFSET, DEFAULT_LIMIT);
		URL baseURL = URL_FACTORY.getNext();
		CollectionResource resource = restCollectionHelper.getCollectionResource(games, baseURL, dao.getNumberOfElements(), DEFAULT_OFFSET, DEFAULT_LIMIT);
		URL expected = restCollectionHelper.getCollectionURL(baseURL, DEFAULT_OFFSET - DEFAULT_LIMIT, DEFAULT_LIMIT);
		assertEquals(expected, resource.getURLToPreviousPage());
	}

	@Test
	public void testGetCollectionResource_URLToNextPage() throws Exception {
		List<Game> games = dao.getAll(DEFAULT_OFFSET, DEFAULT_LIMIT);
		URL baseURL = URL_FACTORY.getNext();
		CollectionResource resource = restCollectionHelper.getCollectionResource(games, baseURL, dao.getNumberOfElements(), DEFAULT_OFFSET, DEFAULT_LIMIT);
		URL expected = restCollectionHelper.getCollectionURL(baseURL, DEFAULT_OFFSET + DEFAULT_LIMIT, DEFAULT_LIMIT);
		assertEquals(expected, resource.getURLToNextPage());
	}

	@Test
	public void testGetCollectionResource_URLToLastPage() throws Exception {
		List<Game> games = dao.getAll(DEFAULT_OFFSET, DEFAULT_LIMIT);
		URL baseURL = URL_FACTORY.getNext();
		long numberOfElements = dao.getNumberOfElements();
		CollectionResource resource = restCollectionHelper.getCollectionResource(games, baseURL, numberOfElements, DEFAULT_OFFSET, DEFAULT_LIMIT);

		long numberOfFullPage = numberOfElements / DEFAULT_LIMIT;
		long lastOffset = numberOfFullPage * (DEFAULT_LIMIT - 1);

		URL expected = restCollectionHelper.getCollectionURL(baseURL, lastOffset, DEFAULT_LIMIT);
		assertEquals(expected, resource.getURLToLastPage());
	}

	@Test
	public void testGetCollectionResource_EmptyList_Items() throws Exception {
		URL baseURL = URL_FACTORY.getNext();
		CollectionResource resource = restCollectionHelper.getCollectionResource(new ArrayList<>(), baseURL, dao.getNumberOfElements(), FIRST_OFFSET, DEFAULT_LIMIT);
		assertTrue(resource.getItems().isEmpty());
	}

	@Test
	public void testGetCollectionResource_EmptyList_Offset() throws Exception {
		URL baseURL = URL_FACTORY.getNext();
		CollectionResource resource = restCollectionHelper.getCollectionResource(new ArrayList<>(), baseURL, dao.getNumberOfElements(), FIRST_OFFSET, DEFAULT_LIMIT);
		assertEquals((Long) FIRST_OFFSET, resource.getOffset());
	}

	@Test
	public void testGetCollectionResource_EmptyList_Limit() throws Exception {
		URL baseURL = URL_FACTORY.getNext();
		CollectionResource resource = restCollectionHelper.getCollectionResource(new ArrayList<>(), baseURL, dao.getNumberOfElements(), FIRST_OFFSET, DEFAULT_LIMIT);
		assertEquals(DEFAULT_LIMIT, resource.getLimit());
	}

	@Test
	public void testGetCollectionResource_EmptyList_URLToFirstPage() throws Exception {
		List<Game> games = dao.getAll(DEFAULT_OFFSET, DEFAULT_LIMIT);
		URL baseURL = URL_FACTORY.getNext();
		CollectionResource resource = restCollectionHelper.getCollectionResource(games, baseURL, dao.getNumberOfElements(), DEFAULT_OFFSET, DEFAULT_LIMIT);
		URL expected = restCollectionHelper.getCollectionURL(baseURL, FIRST_OFFSET, DEFAULT_LIMIT);
		assertEquals(expected, resource.getURLToFirstPage());
	}

	@Test
	public void testGetCollectionResource_EmptyList_URLToPreviousPage() throws Exception {
		List<Game> games = dao.getAll(DEFAULT_OFFSET, DEFAULT_LIMIT);
		URL baseURL = URL_FACTORY.getNext();
		CollectionResource resource = restCollectionHelper.getCollectionResource(games, baseURL, dao.getNumberOfElements(), DEFAULT_OFFSET, DEFAULT_LIMIT);
		assertNull(resource.getURLToPreviousPage());
	}

	@Test
	public void testGetCollectionResource_EmptyList_URLToNextPage() throws Exception {
		List<Game> games = dao.getAll(DEFAULT_OFFSET, DEFAULT_LIMIT);
		URL baseURL = URL_FACTORY.getNext();
		CollectionResource resource = restCollectionHelper.getCollectionResource(games, baseURL, dao.getNumberOfElements(), DEFAULT_OFFSET, DEFAULT_LIMIT);
		assertNull(resource.getURLToNextPage());
	}

	@Test
	public void testGetCollectionResource_EmptyList_URLToLastPage() throws Exception {
		List<Game> games = dao.getAll(DEFAULT_OFFSET, DEFAULT_LIMIT);
		URL baseURL = URL_FACTORY.getNext();
		CollectionResource resource = restCollectionHelper.getCollectionResource(games, baseURL, dao.getNumberOfElements(), DEFAULT_OFFSET, DEFAULT_LIMIT);
		URL expected = restCollectionHelper.getCollectionURL(baseURL, FIRST_OFFSET, DEFAULT_LIMIT);
		assertEquals(expected, resource.getURLToLastPage());
	}

	@Test
	public void testGetCollectionResource_NumberOfElementsLowerThanLimit_Items() throws Exception {
		dao = new GameTestDAO(2);
		URL baseURL = URL_FACTORY.getNext();
		CollectionResource resource = restCollectionHelper.getCollectionResource(new ArrayList<>(), baseURL, dao.getNumberOfElements(), FIRST_OFFSET, DEFAULT_LIMIT);
		List<URL> expectedURLs = restCollectionHelper.convertToURLs(dao.getAll(), baseURL);
		assertEquals(expectedURLs, resource.getItems());
	}

	@Test
	public void testGetCollectionResource_NumberOfElementsLowerThanLimit_Offset() throws Exception {
		dao = new GameTestDAO(2);
		URL baseURL = URL_FACTORY.getNext();
		CollectionResource resource = restCollectionHelper.getCollectionResource(new ArrayList<>(), baseURL, dao.getNumberOfElements(), FIRST_OFFSET, DEFAULT_LIMIT);
		assertEquals((Long) FIRST_OFFSET, resource.getOffset());
	}

	@Test
	public void testGetCollectionResource_NumberOfElementsLowerThanLimit_Limit() throws Exception {
		dao = new GameTestDAO(2);
		URL baseURL = URL_FACTORY.getNext();
		CollectionResource resource = restCollectionHelper.getCollectionResource(new ArrayList<>(), baseURL, dao.getNumberOfElements(), FIRST_OFFSET, DEFAULT_LIMIT);
		assertEquals(DEFAULT_LIMIT, resource.getLimit());
	}

	@Test
	public void testGetCollectionResource_NumberOfElementsLowerThanLimit_URLToFirstPage() throws Exception {
		dao = new GameTestDAO(2);
		List<Game> games = dao.getAll(DEFAULT_OFFSET, DEFAULT_LIMIT);
		URL baseURL = URL_FACTORY.getNext();
		CollectionResource resource = restCollectionHelper.getCollectionResource(games, baseURL, dao.getNumberOfElements(), FIRST_OFFSET, DEFAULT_LIMIT);
		URL expected = restCollectionHelper.getCollectionURL(baseURL, FIRST_OFFSET, DEFAULT_LIMIT);
		assertEquals(expected, resource.getURLToFirstPage());
	}

	@Test
	public void testGetCollectionResource_NumberOfElementsLowerThanLimit_URLToPreviousPage() throws Exception {
		dao = new GameTestDAO(2);
		List<Game> games = dao.getAll(DEFAULT_OFFSET, DEFAULT_LIMIT);
		URL baseURL = URL_FACTORY.getNext();
		CollectionResource resource = restCollectionHelper.getCollectionResource(games, baseURL, dao.getNumberOfElements(), FIRST_OFFSET, DEFAULT_LIMIT);
		assertNull(resource.getURLToPreviousPage());
	}

	@Test
	public void testGetCollectionResource_NumberOfElementsLowerThanLimit_URLToNextPage() throws Exception {
		dao = new GameTestDAO(2);
		List<Game> games = dao.getAll(DEFAULT_OFFSET, DEFAULT_LIMIT);
		URL baseURL = URL_FACTORY.getNext();
		CollectionResource resource = restCollectionHelper.getCollectionResource(games, baseURL, dao.getNumberOfElements(), FIRST_OFFSET, DEFAULT_LIMIT);
		assertNull(resource.getURLToNextPage());
	}

	@Test
	public void testGetCollectionResource_NumberOfElementsLowerThanLimit_URLToLastPage() throws Exception {
		dao = new GameTestDAO(2);
		List<Game> games = dao.getAll(DEFAULT_OFFSET, DEFAULT_LIMIT);
		URL baseURL = URL_FACTORY.getNext();
		CollectionResource resource = restCollectionHelper.getCollectionResource(games, baseURL, dao.getNumberOfElements(), FIRST_OFFSET, DEFAULT_LIMIT);
		URL expected = restCollectionHelper.getCollectionURL(baseURL, FIRST_OFFSET, DEFAULT_LIMIT);
		assertEquals(expected, resource.getURLToLastPage());
	}

	@Test
	public void testGetCollectionResource_NullOffset_Items() throws Exception {
		URL baseURL = URL_FACTORY.getNext();
		CollectionResource resource = restCollectionHelper.getCollectionResource(new ArrayList<>(), baseURL, dao.getNumberOfElements(), null, DEFAULT_LIMIT);
		List<URL> expectedURLs = restCollectionHelper.convertToURLs(dao.getAll(FIRST_OFFSET, DEFAULT_LIMIT), baseURL);
		assertEquals(expectedURLs, resource.getItems());
	}

	@Test
	public void testGetCollectionResource_NullOffset_Offset() throws Exception {
		URL baseURL = URL_FACTORY.getNext();
		CollectionResource resource = restCollectionHelper.getCollectionResource(new ArrayList<>(), baseURL, dao.getNumberOfElements(), null, DEFAULT_LIMIT);
		assertEquals((Long) FIRST_OFFSET, resource.getOffset());
	}

	@Test
	public void testGetCollectionResource_NullOffset_Limit() throws Exception {
		URL baseURL = URL_FACTORY.getNext();
		CollectionResource resource = restCollectionHelper.getCollectionResource(new ArrayList<>(), baseURL, dao.getNumberOfElements(), null, DEFAULT_LIMIT);
		assertEquals(DEFAULT_LIMIT, resource.getLimit());
	}

	@Test
	public void testGetCollectionResource_NullOffset_URLToFirstPage() throws Exception {
		List<Game> games = dao.getAll(DEFAULT_OFFSET, DEFAULT_LIMIT);
		URL baseURL = URL_FACTORY.getNext();
		CollectionResource resource = restCollectionHelper.getCollectionResource(games, baseURL, dao.getNumberOfElements(), null, DEFAULT_LIMIT);
		URL expected = restCollectionHelper.getCollectionURL(baseURL, FIRST_OFFSET, DEFAULT_LIMIT);
		assertEquals(expected, resource.getURLToFirstPage());
	}

	@Test
	public void testGetCollectionResource_NullOffset_URLToPreviousPage() throws Exception {
		List<Game> games = dao.getAll(DEFAULT_OFFSET, DEFAULT_LIMIT);
		URL baseURL = URL_FACTORY.getNext();
		CollectionResource resource = restCollectionHelper.getCollectionResource(games, baseURL, dao.getNumberOfElements(), null, DEFAULT_LIMIT);
		assertNull(resource.getURLToPreviousPage());
	}

	@Test
	public void testGetCollectionResource_NullOffset_URLToNextPage() throws Exception {
		List<Game> games = dao.getAll(DEFAULT_OFFSET, DEFAULT_LIMIT);
		URL baseURL = URL_FACTORY.getNext();
		CollectionResource resource = restCollectionHelper.getCollectionResource(games, baseURL, dao.getNumberOfElements(), null, DEFAULT_LIMIT);
		assertNull(resource.getURLToNextPage());
	}

	@Test
	public void testGetCollectionResource_NullOffset_URLToLastPage() throws Exception {
		List<Game> games = dao.getAll(DEFAULT_OFFSET, DEFAULT_LIMIT);
		URL baseURL = URL_FACTORY.getNext();
		long numberOfElements = dao.getNumberOfElements();
		CollectionResource resource = restCollectionHelper.getCollectionResource(games, baseURL, numberOfElements, DEFAULT_OFFSET, DEFAULT_LIMIT);

		long numberOfFullPage = numberOfElements / DEFAULT_LIMIT;
		long lastOffset = numberOfFullPage * (DEFAULT_LIMIT - 1);

		URL expected = restCollectionHelper.getCollectionURL(baseURL, lastOffset, DEFAULT_LIMIT);
		assertEquals(expected, resource.getURLToLastPage());
	}

	@Test
	public void testGetCollectionResource_NullLimit() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testGetCollectionResource_NegativeLimit() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testGetCollectionResource_LimitZero() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testConvertToURLs() throws Exception {
		throw new NotImplementedException();
	}

	@Test
	public void testConvertToURLs_EmptyList() throws Exception {
		throw new NotImplementedException();
	}
}
