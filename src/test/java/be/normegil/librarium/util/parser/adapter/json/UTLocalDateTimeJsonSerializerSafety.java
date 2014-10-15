package be.normegil.librarium.util.parser.adapter.json;

import be.normegil.librarium.util.DateHelper;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UTLocalDateTimeJsonSerializerSafety {

	private LocalDateTimeJsonSerializer entity;

	@Mock
	private JsonGenerator jgen;
	@Mock
	private SerializerProvider provider;

	@Before
	public void setUp() throws Exception {
		entity = new LocalDateTimeJsonSerializer();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testSerialize_NullDate() throws Exception {
		entity.serialize(null, jgen, provider);

		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
		verify(jgen).writeString(argument.capture());

		assertEquals(null, argument.getValue());
	}

	@Test
	public void testSerialize_NullProvider() throws Exception {
		LocalDateTime time = LocalDateTime.now();
		entity.serialize(time, jgen, null);

		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
		verify(jgen).writeString(argument.capture());

		assertEquals(new DateHelper().format(time), argument.getValue());
	}

	@Test(expected = NullPointerException.class)
	public void testSerialize_NullJsonProvider() throws Exception {
		LocalDateTime time = LocalDateTime.now();
		entity.serialize(time, null, provider);
	}
}