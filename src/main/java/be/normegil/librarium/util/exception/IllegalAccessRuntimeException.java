package be.normegil.librarium.util.exception;

public class IllegalAccessRuntimeException extends RuntimeException {

    public IllegalAccessRuntimeException(final String message, final java.lang.IllegalAccessException e) {
        super(message, e);
    }

    public IllegalAccessRuntimeException(final java.lang.IllegalAccessException e) {
        super(e);
    }
}
