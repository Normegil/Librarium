package be.normegil.librarium.libraries;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTUrlConverter {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private URL.UrlConverter entity;

	@Before
	public void setUp() throws Exception {
		entity = new URL.UrlConverter();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testConvertToDatabaseColumn_Null() throws Exception {
		assertEquals("", entity.convertToDatabaseColumn(null));
	}

	@Test
	public void testConvertToEntityAttribute_Null() throws Exception {
		assertEquals(null, entity.convertToEntityAttribute(null));
	}

	@Test
	public void testConvertToEntityAttribute_Empty() throws Exception {
		assertEquals(null, entity.convertToEntityAttribute(""));
	}

	@Test
	public void testConvertToDatabaseColumn() throws Exception {
		URL url = URL_FACTORY.getNew();
		assertEquals("http://Host:42/File", entity.convertToDatabaseColumn(url));
	}

	@Test
	public void testConvertToEntityAttribute() throws Exception {
		URL url = URL_FACTORY.getNew();
		assertEquals(url, entity.convertToEntityAttribute("http://Host:42/File"));
	}
}
