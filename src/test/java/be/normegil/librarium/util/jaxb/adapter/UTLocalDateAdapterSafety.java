package be.normegil.librarium.util.jaxb.adapter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class UTLocalDateAdapterSafety {

	private static final String EMPTY_STRING = "";
	private LocalDateAdapter entity;

	@Before
	public void setUp() throws Exception {
		entity = new LocalDateAdapter();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testUnmarshal_Null() throws Exception {
		LocalDate date = entity.unmarshal(null);
		assertEquals(null, date);
	}

	@Test
	public void testUnmarshal_Empty() throws Exception {
		LocalDate date = entity.unmarshal(EMPTY_STRING);
		assertEquals(null, date);
	}

	@Test
	public void testMarshal_Null() throws Exception {
		String representation = entity.marshal(null);
		assertEquals(null, representation);
	}
}