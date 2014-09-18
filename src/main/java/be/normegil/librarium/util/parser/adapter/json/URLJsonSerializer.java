package be.normegil.librarium.util.parser.adapter.json;

import be.normegil.librarium.libraries.URL;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class URLJsonSerializer extends JsonSerializer<URL> {
	@Override
	public void serialize(final URL url, final JsonGenerator jgen, final SerializerProvider provider) throws IOException {
		if (url != null) {
			String representation = url.toRepresentation();
			jgen.writeString(representation);
		}
	}
}
