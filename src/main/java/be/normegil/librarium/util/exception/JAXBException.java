package be.normegil.librarium.util.exception;

import be.normegil.librarium.WarningTypes;

@SuppressWarnings(WarningTypes.UNUSED)
public class JAXBException extends RuntimeException {
    public JAXBException(final String message, final javax.xml.bind.JAXBException cause) {
        super(message, cause);
    }

    public JAXBException(final javax.xml.bind.JAXBException cause) {
        super(cause);
    }
}
