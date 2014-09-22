package be.normegil.librarium.util.exception;

public class MalformedURLRuntimeException extends RuntimeException {

    public MalformedURLRuntimeException(final String message, final java.net.MalformedURLException cause) {
        super(message, cause);
    }

    public MalformedURLRuntimeException(final java.net.MalformedURLException cause) {
        super(cause);
    }
}
