package be.normegil.librarium.util.exception;

public class UnknownEnumValueException extends RuntimeException {

	public UnknownEnumValueException(final String message) {
		super(message);
	}

	public UnknownEnumValueException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
