package be.normegil.librarium.util.parser.adapter.jaxb;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class UTUUIDToRESTURLJAXBAdapter {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private UUIDToRESTURLJAXBAdapter entity;
	private URL baseURL;

	@Before
	public void setUp() throws Exception {
		baseURL = URL_FACTORY.getNew();
		entity = new UUIDToRESTURLJAXBAdapter(baseURL);
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testMarshal() throws Exception {
		UUID uuid = UUID.randomUUID();
		assertEquals(baseURL.addToPath(uuid.toString()), entity.marshal(uuid));
	}

	@Test
	public void testUnmarshal() throws Exception {
		UUID uuid = UUID.randomUUID();
		URL toParse = URL_FACTORY.getNew().addToPath(uuid);
		assertEquals(uuid, entity.unmarshal(toParse));
	}
}
