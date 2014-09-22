package be.normegil.librarium.util.exception;

public class XMLStreamRuntimeException extends RuntimeException {
	public XMLStreamRuntimeException(final String message, final javax.xml.stream.XMLStreamException cause) {
		super(message, cause);
	}

	public XMLStreamRuntimeException(final javax.xml.stream.XMLStreamException cause) {
		super(cause);
	}
}
