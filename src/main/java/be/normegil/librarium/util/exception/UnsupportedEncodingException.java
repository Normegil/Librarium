package be.normegil.librarium.util.exception;

public class UnsupportedEncodingException extends RuntimeException {
	public UnsupportedEncodingException(final String message, final java.io.UnsupportedEncodingException cause) {
		super(message, cause);
	}

	public UnsupportedEncodingException(final java.io.UnsupportedEncodingException cause) {
		super(cause);
	}
}
