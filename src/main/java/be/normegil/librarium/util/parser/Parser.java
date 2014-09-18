package be.normegil.librarium.util.parser;

import be.normegil.librarium.util.exception.IOException;
import be.normegil.librarium.util.exception.NoSuchParserException;

import javax.validation.constraints.NotNull;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Parser<E> {

	private final Class<?>[] contextClasses;
	@NotNull
	private Class<E> entityClass;

	private Map<DocumentType, DocumentParser<E>> parsers;

	public Parser(@NotNull Class<E> entityClass, Class<?>... contextClasses) {
		this.entityClass = entityClass;
		this.contextClasses = contextClasses;
		parsers = loadParsers(entityClass, contextClasses);
	}

	public E from(@NotNull DocumentType type, @NotNull InputStream stream) {
		DocumentParser<E> parser = parsers.get(type);
		if (parser == null) {
			throw new NoSuchParserException("Cannot find parser for " + type);
		} else {
			E value = parser.from(stream);
			if (value == null) {
				throw new IOException("Could not load object");
			}
			return value;
		}
	}

	public void to(@NotNull E entity, @NotNull DocumentType type, @NotNull OutputStream stream) {
		DocumentParser<E> parser = parsers.get(type);
		if (parser == null) {
			throw new NoSuchParserException("Cannot find parser for " + type);
		} else {
			parser.to(entity, stream);
		}
	}

	private Map<DocumentType, DocumentParser<E>> loadParsers(final Class<E> entityClass, final Class<?>[] contextClasses) {
		Map<DocumentType, DocumentParser<E>> parsers = new HashMap<>();
		parsers.put(DocumentType.JSON, new JacksonHelper<>(entityClass));
		parsers.put(DocumentType.XML, new JAXBHelper<>(entityClass, contextClasses));
		return parsers;
	}

	public enum DocumentType {
		JSON,
		XML;
	}
}
