package be.normegil.librarium.libraries;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.util.jaxb.adapter.URLAdapter;
import be.normegil.librarium.validation.constraint.NotEmpty;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

@XmlJavaTypeAdapter(URLAdapter.class)
@Convert(converter = URL.UrlConverter.class)
public class URL implements Comparable<URL> {

	@NotNull
	private java.net.URL url;

	public URL(@NotEmpty String url) {
		try {
			this.url = new java.net.URL(url);
		} catch (MalformedURLException e) {
			throw new be.normegil.librarium.util.exception.MalformedURLException(e);
		}
	}

	public URL(@NotEmpty String protocol, @NotEmpty String host, @NotNull Integer port, @NotNull String filePath) {
		try {
			url = new java.net.URL(protocol, host, port, filePath);
		} catch (MalformedURLException e) {
			throw new be.normegil.librarium.util.exception.MalformedURLException(e);
		}
	}

	public URL(@NotNull URL url) {
		this.url = url.url;
	}

	public URL(@NotNull java.net.URL url) {
		this.url = url;
	}

	// For JAXB
	@SuppressWarnings(WarningTypes.UNUSED)
	private URL() {
	}

	public URI toURI() {
		try {
			return url.toURI();
		} catch (URISyntaxException e) {
			throw new be.normegil.librarium.util.exception.URISyntaxException(e);
		}
	}

	public String toRepresentation() {
		return getProtocol() + "://" + url.getHost() + ":" + url.getPort() + url.getFile();
	}

	public String getProtocol() {
		return url.getProtocol();
	}

	public String getHost() {
		return url.getHost();
	}

	public int getPort() {
		return url.getPort();
	}

	public String getFilePath() {
		return url.getFile();
	}

	public java.net.URL getOriginalURL() {
		return url;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		URL rhs = (URL) obj;
		return new EqualsBuilder()
				.append(this.url, rhs.url)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(url)
				.toHashCode();
	}

	@Override
	public int compareTo(final URL o) {
		if (o != null) {
			return new CompareToBuilder()
					.append(url.getHost(), o.getOriginalURL().getHost())
					.append(url.getFile(), o.getOriginalURL().getFile())
					.append(url.getProtocol(), o.getOriginalURL().getProtocol())
					.append(url.getPort(), o.getOriginalURL().getPort())
					.toComparison();
		} else {
			return Constants.Comparator.PRIORITY_SECOND;
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ApplicationProperties.TO_STRING_STYLE)
				.append("url", url)
				.toString();
	}

	@Converter(autoApply = true)
	public static class UrlConverter implements AttributeConverter<URL, String> {

		private static final String EMPTY_STRING = "";

		@Override
		public String convertToDatabaseColumn(final URL attribute) {
			return attribute == null ? "" : attribute.toRepresentation();
		}

		@Override
		public URL convertToEntityAttribute(final String dbData) {
			return !isDatabaseDataValid(dbData) ? null : new URL(dbData);
		}

		private boolean isDatabaseDataValid(final String dbData) {
			return dbData != null && !dbData.isEmpty();
		}
	}

	public static class Comparator implements java.util.Comparator<URL> {
		@Override
		public int compare(final URL o1, final URL o2) {
			return new CompareToBuilder()
					.append(o1, o2)
					.toComparison();
		}
	}
}
