package be.normegil.librarium.util.exception;

public class InstantiationException extends RuntimeException {

	public InstantiationException(final String message, final java.lang.InstantiationException e) {
		super(message, e);
	}

	public InstantiationException(final java.lang.InstantiationException e) {
		super(e);
	}
}
