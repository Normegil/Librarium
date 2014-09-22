package be.normegil.librarium.util.parser;

import be.normegil.librarium.util.exception.IORuntimeException;
import be.normegil.librarium.util.exception.NoSuchParserRuntimeException;

import javax.validation.constraints.NotNull;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Parser<E> {

	private Map<DocumentType, DocumentParser<E>> parsers;

	public Parser(@NotNull Class<E> entityClass, Class<?>... contextClasses) {
		parsers = loadParsers(entityClass, contextClasses);
	}

	public E from(@NotNull DocumentType type, @NotNull InputStream stream) {
		DocumentParser<E> parser = parsers.get(type);
		if (parser == null) {
			throw new NoSuchParserRuntimeException("Cannot find parser for " + type);
		} else {
			E value = parser.from(stream);
			if (value == null) {
				throw new IORuntimeException("Could not load object");
			}
			return value;
		}
	}

	public void to(@NotNull E entity, @NotNull DocumentType type, @NotNull OutputStream stream) {
		DocumentParser<E> parser = parsers.get(type);
		if (parser == null) {
			throw new NoSuchParserRuntimeException("Cannot find parser for " + type);
		} else {
			parser.to(entity, stream);
		}
	}

	private Map<DocumentType, DocumentParser<E>> loadParsers(final Class<E> entityClass, final Class<?>[] contextClasses) {
		Map<DocumentType, DocumentParser<E>> parsers = new HashMap<>();
		parsers.put(DocumentType.JSON, new JacksonParser<>(entityClass));
		parsers.put(DocumentType.XML, new JAXBHelper<>(entityClass, contextClasses));
		return parsers;
	}

	public enum DocumentType {
		JSON,
		XML
	}
}
