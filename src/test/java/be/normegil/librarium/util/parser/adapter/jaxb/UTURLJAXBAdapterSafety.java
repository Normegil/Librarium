package be.normegil.librarium.util.parser.adapter.jaxb;

import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.util.parser.adapter.jaxb.URLJAXBAdapter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTURLJAXBAdapterSafety {

	private static final String EMPTY_STRING = "";
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
	public void testUnmarshal_Null() throws Exception {
		URL url = entity.unmarshal(null);
		assertEquals(null, url);
	}

	@Test
	public void testUnmarshal_Empty() throws Exception {
		URL url = entity.unmarshal(EMPTY_STRING);
		assertEquals(null, url);
	}

	@Test
	public void testMarshal_Null() throws Exception {
		String representation = entity.marshal(null);
		assertEquals(null, representation);
	}
}