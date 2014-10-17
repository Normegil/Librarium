package be.normegil.librarium.util.parser.adapter.json;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UTURLJsonDeserializer {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);

	private URLJsonDeserializer entity;

	@Mock
	private JsonParser parser;

	@Mock
	private DeserializationContext context;

	@Before
	public void setUp() throws Exception {
		entity = new URLJsonDeserializer();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testDeserialize() throws Exception {
		URL expected = URL_FACTORY.getNew();
		when(parser.getText())
				.thenReturn(expected.toRepresentation());
		URL toTest = entity.deserialize(parser, context);
		assertEquals(expected, toTest);
	}

}
