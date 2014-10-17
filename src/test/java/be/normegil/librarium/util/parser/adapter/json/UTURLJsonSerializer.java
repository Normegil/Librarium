package be.normegil.librarium.util.parser.adapter.json;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class UTURLJsonSerializer {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private URLJsonSerializer entity;

	@Mock
	private JsonGenerator jgen;
	@Mock
	private SerializerProvider provider;

	@Before
	public void setUp() throws Exception {
		entity = new URLJsonSerializer();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testSerialize_NullDate() throws Exception {
		URL url = URL_FACTORY.getNew();
		entity.serialize(url, jgen, provider);

		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
		verify(jgen).writeString(argument.capture());

		assertEquals(url.toRepresentation(), argument.getValue());
	}
}
