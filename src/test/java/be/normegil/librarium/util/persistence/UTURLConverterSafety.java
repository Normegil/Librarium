package be.normegil.librarium.util.persistence;

import be.normegil.librarium.libraries.URL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTURLConverterSafety {

	private static final String EMPTY_STRING = "";
	private URLConverter entity;

	@Before
	public void setUp() throws Exception {
		entity = new URLConverter();
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
		URL url = entity.convertToEntityAttribute(EMPTY_STRING);
		assertEquals(null, url);
	}

	@Test
	public void testConvertToEntityAttribute_Empty() throws Exception {
		URL url = entity.convertToEntityAttribute(null);
		assertEquals(null, url);
	}
}