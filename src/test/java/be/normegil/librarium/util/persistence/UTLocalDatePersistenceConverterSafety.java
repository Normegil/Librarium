package be.normegil.librarium.util.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class UTLocalDatePersistenceConverterSafety {

	private static final String EMPTY_STRING = "";
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
	public void testConvertToDatabaseColumn_Null() throws Exception {
		String databaseValue = entity.convertToDatabaseColumn(null);
		assertEquals(null, databaseValue);
	}

	@Test
	public void testConvertToEntityAttribute_Null() throws Exception {
		LocalDate date = entity.convertToEntityAttribute(EMPTY_STRING);
		assertEquals(null, date);
	}

	@Test
	public void testConvertToEntityAttribute_Empty() throws Exception {
		LocalDate date = entity.convertToEntityAttribute(null);
		assertEquals(null, date);
	}
}