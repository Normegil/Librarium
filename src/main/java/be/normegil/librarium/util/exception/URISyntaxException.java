package be.normegil.librarium.util.exception;

public class URISyntaxException extends RuntimeException {

    public URISyntaxException(final String message, final java.net.URISyntaxException cause) {
        super(message, cause);
    }

    public URISyntaxException(final java.net.URISyntaxException cause) {
        super(cause);
    }
}
