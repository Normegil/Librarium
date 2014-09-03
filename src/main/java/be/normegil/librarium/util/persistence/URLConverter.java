package be.normegil.librarium.util.persistence;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
@SuppressWarnings(WarningTypes.UNUSED)
public class URLConverter implements AttributeConverter<URL, String> {
	@Override
	public String convertToDatabaseColumn(final URL url) {
		if (url != null) {
			return url.toRepresentation();
		} else {
			return null;
		}
	}

	@Override
	public URL convertToEntityAttribute(final String url) {
		if (url == null || url.isEmpty()) {
			return null;
		} else {
			return new URL(url);
		}
	}
}
