package be.normegil.librarium.util.exception;

public class SAXException extends RuntimeException{
    public SAXException(final String message, final org.xml.sax.SAXException cause) {
        super(message, cause);
    }

    public SAXException(final org.xml.sax.SAXException cause) {
        super(cause);
    }
}
