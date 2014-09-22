package be.normegil.librarium.util.exception;

public class NoSuchMethodRuntimeException extends RuntimeException {

    public NoSuchMethodRuntimeException(final String message, final java.lang.NoSuchMethodException cause) {
        super(message, cause);
    }

    public NoSuchMethodRuntimeException(final java.lang.NoSuchMethodException cause) {
        super(cause);
    }
}
