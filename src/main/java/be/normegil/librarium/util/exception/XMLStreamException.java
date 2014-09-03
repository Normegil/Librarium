package be.normegil.librarium.util.exception;

public class XMLStreamException extends RuntimeException {
	public XMLStreamException(final String message, final javax.xml.stream.XMLStreamException cause) {
		super(message, cause);
	}

	public XMLStreamException(final javax.xml.stream.XMLStreamException cause) {
		super(cause);
	}
}
