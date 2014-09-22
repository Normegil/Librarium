package be.normegil.librarium.util.parser;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.util.exception.JAXBRuntimeException;

import javax.validation.constraints.NotNull;
import javax.xml.bind.*;
import javax.xml.validation.Schema;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JAXBHelper<E> implements DocumentParser<E> {

	private final JAXBContext jaxbContext;
	private final ValidationEventHandler eventHandler;
	private final Schema schema;

	public JAXBHelper(@NotNull Class<E> entityClass, @NotNull Class<?>... jaxbContextClasses) {
		this(null, null, entityClass, jaxbContextClasses);
	}

	public JAXBHelper(@NotNull Schema schema, @NotNull ValidationEventHandler eventHandler, @NotNull Class<E> entityClass, @NotNull Class<?>... jaxbContextClasses) {
		try {
			this.schema = schema;
			this.eventHandler = eventHandler;
			jaxbContext = JAXBContext.newInstance(toContextClasses(entityClass, jaxbContextClasses));
		} catch (JAXBException e) {
			throw new JAXBRuntimeException(e);
		}
	}

	@Override
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	public E from(@NotNull InputStream stream) {
		try {
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			if (schema != null) {
				unmarshaller.setEventHandler(eventHandler);
				unmarshaller.setSchema(schema);
			}

			Object unmarshaled = unmarshaller.unmarshal(stream);
			return (E) unmarshaled;
		} catch (JAXBException e) {
			throw new JAXBRuntimeException(e);
		}
	}

	@Override
	public void to(@NotNull E entity, @NotNull OutputStream stream) {
		try {
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, ApplicationProperties.ENCODING);
			if (schema != null) {
				marshaller.setEventHandler(eventHandler);
				marshaller.setSchema(schema);
			}

			marshaller.marshal(entity, stream);
		} catch (JAXBException e) {
			throw new JAXBRuntimeException(e);
		}
	}

	private Class[] toContextClasses(final Class<E> entityClass, final Class<?>[] jaxbContextClasses) {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(entityClass);
		classes.addAll(Arrays.asList(jaxbContextClasses));
		Class[] someClass = new Class[classes.size()];
		someClass = classes.toArray(someClass);
		return someClass;
	}
}
