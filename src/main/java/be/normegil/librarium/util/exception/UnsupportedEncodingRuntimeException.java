package be.normegil.librarium.util.exception;

public class UnsupportedEncodingRuntimeException extends RuntimeException {
	public UnsupportedEncodingRuntimeException(final String message, final java.io.UnsupportedEncodingException cause) {
		super(message, cause);
	}

	public UnsupportedEncodingRuntimeException(final java.io.UnsupportedEncodingException cause) {
		super(cause);
	}
}
