package be.normegil.librarium.util.exception;

public class IOException extends RuntimeException {

    public IOException(final String message, final java.io.IOException cause) {
        super(message, cause);
    }

    public IOException(final java.io.IOException cause) {
        super(cause);
    }
}
