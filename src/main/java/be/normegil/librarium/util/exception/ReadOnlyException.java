package be.normegil.librarium.util.exception;

public class ReadOnlyException extends RuntimeException{

	public ReadOnlyException(final String message) {
		super(message);
	}

	public ReadOnlyException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ReadOnlyException(final Throwable cause) {
		super(cause);
	}
}
