package be.normegil.librarium.util.parser.adapter.jaxb;

import be.normegil.librarium.util.DateHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class UTLocalDateTimeJAXBAdapter {

	private static final DateHelper DATE_HELPER = new DateHelper();
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
	public void testMarshal() throws Exception {
		LocalDateTime now = LocalDateTime.now();
		assertEquals(DATE_HELPER.format(now), entity.marshal(now));
	}

	@Test
	public void testUnmarshal() throws Exception {
		LocalDateTime now = LocalDateTime.now();
		String toParse = DATE_HELPER.format(now);
		assertEquals(now, entity.unmarshal(toParse));
	}
}
