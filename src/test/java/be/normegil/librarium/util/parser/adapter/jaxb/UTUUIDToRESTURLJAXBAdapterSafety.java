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

public class UTUUIDToRESTURLJAXBAdapterSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private UUIDToRESTURLJAXBAdapter entity;

	@Before
	public void setUp() throws Exception {
		entity = new UUIDToRESTURLJAXBAdapter(URL_FACTORY.getNew());
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testUnmarshal_Null() throws Exception {
		UUID uuid = entity.unmarshal(null);
		assertEquals(null, uuid);
	}

	@Test
	public void testMarshal_Null() throws Exception {
		URL representation = entity.marshal(null);
		assertEquals(null, representation);
	}

}
