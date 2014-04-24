package be.normegil.librarium.util.exception;

public class MalformedURLException extends RuntimeException {

    public MalformedURLException(final String message, final java.net.MalformedURLException cause) {
        super(message, cause);
    }

    public MalformedURLException(final java.net.MalformedURLException cause) {
        super(cause);
    }
}
