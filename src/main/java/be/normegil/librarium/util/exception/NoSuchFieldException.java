package be.normegil.librarium.util.exception;

public class NoSuchFieldException extends RuntimeException {

	public NoSuchFieldException(final String message, final java.lang.NoSuchFieldException cause) {
		super(message, cause);
	}

	public NoSuchFieldException(final java.lang.NoSuchFieldException cause) {
		super(cause);
	}

	public NoSuchFieldException(final String message) {
		super(message);
	}
}
