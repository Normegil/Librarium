package be.normegil.librarium.util.exception;

public class NoSuchParserRuntimeException extends RuntimeException {

	public NoSuchParserRuntimeException(final String message) {
		super(message);
	}

	public NoSuchParserRuntimeException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
