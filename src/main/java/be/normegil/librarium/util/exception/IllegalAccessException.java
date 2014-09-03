package be.normegil.librarium.util.exception;

public class IllegalAccessException extends RuntimeException {

    public IllegalAccessException(final String message, final java.lang.IllegalAccessException e) {
        super(message, e);
    }

    public IllegalAccessException(final java.lang.IllegalAccessException e) {
        super(e);
    }
}
