package be.normegil.librarium.util.parser.adapter.json;

import be.normegil.librarium.util.DateHelper;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UTLocalDateTimeJsonDeserializer {

	private LocalDateTimeJsonDeserializer entity;

	@Mock
	private JsonParser parser;

	@Mock
	private DeserializationContext context;

	@Before
	public void setUp() throws Exception {
		entity = new LocalDateTimeJsonDeserializer();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testDeserialize() throws Exception {
		LocalDateTime expected = LocalDateTime.now();
		when(parser.getText())
				.thenReturn(new DateHelper().format(expected));
		LocalDateTime time = entity.deserialize(parser, context);
		assertEquals(expected, time);
	}
}
