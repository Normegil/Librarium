package be.normegil.librarium.util.parser.adapter.json;

import be.normegil.librarium.libraries.URL;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class URLJsonDeserializer extends JsonDeserializer<URL> {

	@Override
	public URL deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
		return new URL(jp.getText());
	}
}
