package be.normegil.librarium.util.parser.adapter;

import be.normegil.librarium.util.DateHelper;
import be.normegil.librarium.util.parser.adapter.jaxb.LocalDateJAXBAdapter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class UTLocalDateAdapter {

	private static final DateHelper DATE_HELPER = new DateHelper();
	private LocalDateJAXBAdapter entity;

	@Before
	public void setUp() throws Exception {
		entity = new LocalDateJAXBAdapter();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testMarshal() throws Exception {
		LocalDate now = LocalDate.now();
		assertEquals(DATE_HELPER.format(now), entity.marshal(now));
	}

	@Test
	public void testUnmarshal() throws Exception {
		LocalDate now = LocalDate.now();
		String toParse = DATE_HELPER.format(now);
		assertEquals(now, entity.unmarshal(toParse));
	}
}
