package be.normegil.librarium.util.exception;

import be.normegil.librarium.WarningTypes;

@SuppressWarnings(WarningTypes.UNUSED)
public class JAXBRuntimeException extends RuntimeException {
    public JAXBRuntimeException(final String message, final javax.xml.bind.JAXBException cause) {
        super(message, cause);
    }

    public JAXBRuntimeException(final javax.xml.bind.JAXBException cause) {
        super(cause);
    }
}
