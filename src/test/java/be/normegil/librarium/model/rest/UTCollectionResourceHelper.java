package be.normegil.librarium.model.rest;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTCollectionResourceHelper {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private CollectionResource.Helper entity;

	@Before
	public void setUp() throws Exception {
		entity = CollectionResource.helper();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testGetCollectionURL() throws Exception {
		URL url = URL_FACTORY.getNew();
		long offset = 5L;
		int limit = 25;
		URL toTest = entity.getCollectionURL(url, offset, limit);
		URL expected = url.addToPath(Constants.URL.PARAMETER_SEPARATOR + "offset=" + offset + "&limit=" + limit);
		assertEquals(expected, toTest);
	}

	@Test
	public void testGetBaseURL() throws Exception {
		URL url = URL_FACTORY.getNew();
		long offset = 5L;
		int limit = 25;
		URL urlToParse = url.addToPath(Constants.URL.PARAMETER_SEPARATOR + "offset=" + offset + "&limit=" + limit);
		URL toTest = entity.getBaseURL(urlToParse);
		assertEquals(url, toTest);
	}
}
