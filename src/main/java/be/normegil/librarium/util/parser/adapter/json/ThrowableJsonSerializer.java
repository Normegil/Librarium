package be.normegil.librarium.util.parser.adapter.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ThrowableJsonSerializer extends JsonSerializer<Throwable> {
	@Override
	public void serialize(final Throwable value, final JsonGenerator jgen, final SerializerProvider provider) throws IOException, JsonProcessingException {

	}
}
