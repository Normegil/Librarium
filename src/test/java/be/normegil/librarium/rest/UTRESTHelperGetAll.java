package be.normegil.librarium.rest;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.dao.DAO;
import be.normegil.librarium.model.dao.GameTestDAO;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ContextResolver;
import javax.xml.bind.Marshaller;
import java.net.URI;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UTRESTHelperGetAll {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private static final long DEFAULT_OFFSET = 5L;
	private static final int DEFAULT_LIMIT = ApplicationProperties.REST.DEFAULT_LIMIT + 1;
	private static final int NUMBER_OF_GAMES = ApplicationProperties.REST.DEFAULT_LIMIT * 2;
	private RESTHelper<Game> restHelper;
	private DAO<Game> dao;
	private URI baseURI;

	@Mock
	private ContextResolver<Marshaller> context;

	@Mock
	private Updater<Game> updater;

	@Mock
	private UriInfo info;

	@Spy
	private RESTCollectionHelper helper;


	@Before
	public void setUp() throws Exception {
		dao = new GameTestDAO(NUMBER_OF_GAMES);
		restHelper = new RESTHelper<>(dao, context, updater);

		baseURI = URL_FACTORY.getNext().toURI();
		when(info.getBaseUri())
				.thenReturn(baseURI);
	}

	@After
	public void tearDown() throws Exception {
		restHelper = null;
		dao = null;
	}

	@Test
	public void testGetAll_RESTCollectionResourceCalled() throws Exception {
		restHelper.getAll(info, DEFAULT_OFFSET, DEFAULT_LIMIT);
		URL baseURL = new URL(baseURI);
		verify(helper, times(1))
				.getCollectionResource(dao.getAll(), baseURL, dao.getNumberOfElements(), DEFAULT_OFFSET, DEFAULT_LIMIT);
	}

	@Test
	public void testGetAll_ResponseStatus() throws Exception {
		Response response = restHelper.getAll(info, DEFAULT_OFFSET, DEFAULT_LIMIT);
		assertEquals(response.getStatus(), HttpStatus.SC_OK);
	}
}
