package be.normegil.librarium.util.exception;

public class NoSuchFieldRuntimeException extends RuntimeException {

	public NoSuchFieldRuntimeException(final String message, final java.lang.NoSuchFieldException cause) {
		super(message, cause);
	}

	public NoSuchFieldRuntimeException(final java.lang.NoSuchFieldException cause) {
		super(cause);
	}

	public NoSuchFieldRuntimeException(final String message) {
		super(message);
	}
}
