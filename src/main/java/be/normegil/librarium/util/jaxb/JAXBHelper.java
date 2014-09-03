package be.normegil.librarium.util.jaxb;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.util.InputStreamHelper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamReader;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;

import javax.validation.constraints.NotNull;
import javax.xml.bind.*;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.validation.Schema;
import java.io.*;

public class JAXBHelper<E> {

	private final JAXBContext jaxbContext;
	private final ValidationEventHandler eventHandler;
	private final Schema schema;

	public JAXBHelper(@NotNull Class<?>... jaxbContextClasses) {
		this(null, null, jaxbContextClasses);
	}

	public JAXBHelper(@NotNull Schema schema, @NotNull ValidationEventHandler eventHandler, Class<?>... jaxbContextClasses) {
		try {
			this.schema = schema;
			this.eventHandler = eventHandler;
			jaxbContext = JAXBContext.newInstance(jaxbContextClasses);
		} catch (JAXBException e) {
			throw new be.normegil.librarium.util.exception.JAXBException(e);
		}
	}

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	public E from(@NotNull DocumentType type,@NotNull  InputStream stream) {
		try {
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			if (schema != null) {
				unmarshaller.setEventHandler(eventHandler);
				unmarshaller.setSchema(schema);
			}

			if (DocumentType.JSON.equals(type)) {
				Object unmarshaled = unmarshaller.unmarshal(getJSONStreamReader(stream));
				return (E) unmarshaled;
			} else if (DocumentType.XML.equals(type)) {
				Object unmarshaled = unmarshaller.unmarshal(stream);
				return (E) unmarshaled;
			} else {
				throw new UnsupportedOperationException("Document type " + type.toString() + " not supported for unmarshalling");
			}
		} catch (JAXBException e) {
			throw new be.normegil.librarium.util.exception.JAXBException(e);
		}
	}

	public void to(@NotNull E entity,@NotNull DocumentType type,@NotNull  OutputStream stream) {
		try {
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, ApplicationProperties.ENCODING);
			if (schema != null) {
				marshaller.setEventHandler(eventHandler);
				marshaller.setSchema(schema);
			}

			if (DocumentType.JSON.equals(type)) {
				marshaller.marshal(entity, getJSONStreamWriter(stream));
			} else {
				marshaller.marshal(entity, stream);
			}
		} catch (JAXBException e) {
			throw new be.normegil.librarium.util.exception.JAXBException(e);
		}
	}

	private XMLStreamWriter getJSONStreamWriter(final OutputStream stream) {
		try {
			Configuration config = new Configuration();
			MappedNamespaceConvention mappedNamespaceConvention = new MappedNamespaceConvention(config);
			Writer writer = new OutputStreamWriter(stream, ApplicationProperties.ENCODING);
			return new MappedXMLStreamWriter(mappedNamespaceConvention, writer);
		} catch (UnsupportedEncodingException e) {
			throw new be.normegil.librarium.util.exception.UnsupportedEncodingException(e);
		}
	}

	private XMLStreamReader getJSONStreamReader(final InputStream stream) {
		try {
			Configuration config = new Configuration();
			MappedNamespaceConvention mappedNamespaceConvention = new MappedNamespaceConvention(config);
			JSONObject jsonObject = new JSONObject(new InputStreamHelper().toString(stream));
			return new MappedXMLStreamReader(jsonObject, mappedNamespaceConvention);
		} catch (JSONException e) {
			throw new be.normegil.librarium.util.exception.JSONException(e);
		} catch (XMLStreamException e) {
			throw new be.normegil.librarium.util.exception.XMLStreamException(e);
		}
	}

	public enum DocumentType {
		JSON, XML;
	}
}
