package be.normegil.librarium.util.exception;

public class InvocationTargetException extends RuntimeException {

    public InvocationTargetException(final String message, final java.lang.reflect.InvocationTargetException e) {
        super(message, e);
    }

    public InvocationTargetException(final java.lang.reflect.InvocationTargetException e) {
        super(e);
    }
}
