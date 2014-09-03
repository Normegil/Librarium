package be.normegil.librarium.util.exception;

public class NoSuchMethodException extends RuntimeException {

    public NoSuchMethodException(final String message, final java.lang.NoSuchMethodException cause) {
        super(message, cause);
    }

    public NoSuchMethodException(final java.lang.NoSuchMethodException cause) {
        super(cause);
    }
}
