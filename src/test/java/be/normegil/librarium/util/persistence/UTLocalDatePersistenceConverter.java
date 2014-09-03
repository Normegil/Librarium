package be.normegil.librarium.util.persistence;

import be.normegil.librarium.util.DateHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class UTLocalDatePersistenceConverter {

	private static final DateHelper DATE_HELPER = new DateHelper();
	private LocalDatePersistenceConverter entity;

	@Before
	public void setUp() throws Exception {
		entity = new LocalDatePersistenceConverter();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testConvertToDatabaseColumn() throws Exception {
		LocalDate now = LocalDate.now();
		assertEquals(DATE_HELPER.format(now), entity.convertToDatabaseColumn(now));
	}

	@Test
	public void testConvertToEntityAttribute() throws Exception {
		LocalDate now = LocalDate.now();
		String toParse = DATE_HELPER.format(now);
		assertEquals(now, entity.convertToEntityAttribute(toParse));
	}
}
