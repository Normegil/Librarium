package be.normegil.librarium.util.parser;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class JacksonHelper<E> implements DocumentParser<E> {

	private Class<E> entityClass;
	private final ObjectMapper mapper;

	public JacksonHelper(@NotNull final Class<E> entityClass) {
		this.entityClass = entityClass;
		mapper = new ObjectMapper();
	}

	@Override
	public E from(@NotNull final InputStream stream) {
		try {
			return mapper.readValue(stream, entityClass);
		} catch (IOException e) {
			throw new be.normegil.librarium.util.exception.IOException(e);
		}
	}

	@Override
	public void to(@NotNull final E entity, @NotNull final OutputStream stream) {
		try {
			mapper.writeValue(stream, entity);
		} catch (IOException e) {
			throw new be.normegil.librarium.util.exception.IOException(e);
		}
	}
}
