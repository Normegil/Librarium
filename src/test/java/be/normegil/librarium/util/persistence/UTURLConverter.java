package be.normegil.librarium.util.persistence;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTURLConverter {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> FACTORY = FactoryRepository.get(URL.class);
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
	public void testConvertToDatabaseColumn() throws Exception {
		URL url = FACTORY.getNext();
		assertEquals(url.toRepresentation(), entity.convertToDatabaseColumn(url));
	}

	@Test
	public void testConvertToEntityAttribute() throws Exception {
		URL url = FACTORY.getNext();
		String toParse = url.toRepresentation();
		assertEquals(url, entity.convertToEntityAttribute(toParse));
	}
}
