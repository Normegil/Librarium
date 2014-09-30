package be.normegil.librarium.util.exception;

public class NoSuchEntityException extends RuntimeException {

	public NoSuchEntityException(final String message) {
		super(message);
	}

	public NoSuchEntityException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
