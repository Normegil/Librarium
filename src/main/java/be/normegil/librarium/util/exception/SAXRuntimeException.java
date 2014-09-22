package be.normegil.librarium.util.exception;

import be.normegil.librarium.WarningTypes;

@SuppressWarnings(WarningTypes.UNUSED)
public class SAXRuntimeException extends RuntimeException {
    public SAXRuntimeException(final String message, final org.xml.sax.SAXException cause) {
        super(message, cause);
    }

    public SAXRuntimeException(final org.xml.sax.SAXException cause) {
        super(cause);
    }
}
