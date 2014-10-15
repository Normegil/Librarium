package be.normegil.librarium.util.parser.adapter.jaxb;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class UTLocalDateTimeJAXBAdapterSafety {

	private static final String EMPTY_STRING = "";
	private LocalDateTimeJAXBAdapter entity;

	@Before
	public void setUp() throws Exception {
		entity = new LocalDateTimeJAXBAdapter();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testUnmarshal_Null() throws Exception {
		LocalDateTime date = entity.unmarshal(null);
		assertEquals(null, date);
	}

	@Test
	public void testUnmarshal_Empty() throws Exception {
		LocalDateTime time = entity.unmarshal(EMPTY_STRING);
		assertEquals(null, time);
	}

	@Test
	public void testMarshal_Null() throws Exception {
		String representation = entity.marshal(null);
		assertEquals(null, representation);
	}

}
