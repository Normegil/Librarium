package be.normegil.librarium.util.exception;

import be.normegil.librarium.WarningTypes;

@SuppressWarnings(WarningTypes.UNUSED)
public class SAXException extends RuntimeException {
    public SAXException(final String message, final org.xml.sax.SAXException cause) {
        super(message, cause);
    }

    public SAXException(final org.xml.sax.SAXException cause) {
        super(cause);
    }
}
