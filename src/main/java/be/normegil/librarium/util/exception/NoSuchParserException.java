package be.normegil.librarium.util.exception;

public class NoSuchParserException extends RuntimeException {

	public NoSuchParserException(final String message) {
		super(message);
	}

	public NoSuchParserException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
