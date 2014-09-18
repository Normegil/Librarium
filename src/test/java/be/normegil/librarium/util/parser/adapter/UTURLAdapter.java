package be.normegil.librarium.util.parser.adapter;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.util.parser.adapter.jaxb.URLJAXBAdapter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTURLAdapter {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> FACTORY = FactoryRepository.get(URL.class);
	private URLJAXBAdapter entity;

	@Before
	public void setUp() throws Exception {
		entity = new URLJAXBAdapter();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testMarshal() throws Exception {
		URL url = FACTORY.getNext();
		assertEquals(url.toRepresentation(), entity.marshal(url));
	}

	@Test
	public void testUnmarshal() throws Exception {
		URL url = FACTORY.getNext();
		String toParse = url.toRepresentation();
		assertEquals(url, entity.unmarshal(toParse));
	}
}
