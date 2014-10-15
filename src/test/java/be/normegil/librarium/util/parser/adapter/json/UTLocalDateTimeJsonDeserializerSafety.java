package be.normegil.librarium.util.parser.adapter.json;

import be.normegil.librarium.util.DateHelper;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UTLocalDateTimeJsonDeserializerSafety {

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
	public void testDeserialize_NullJsonParser() throws Exception {
		LocalDateTime time = entity.deserialize(null, context);
		assertNull(time);
	}

	@Test
	public void testDeserialize_NullDeserializationContext() throws Exception {
		LocalDateTime expected = LocalDateTime.now();
		when(parser.getText())
				.thenReturn(new DateHelper().format(expected));
		LocalDateTime time = entity.deserialize(parser, null);
		assertEquals(expected, time);
	}
}