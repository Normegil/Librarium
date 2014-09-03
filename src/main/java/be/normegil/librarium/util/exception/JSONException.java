package be.normegil.librarium.util.exception;

public class JSONException extends RuntimeException{
	public JSONException(final String message, final org.codehaus.jettison.json.JSONException cause) {
		super(message, cause);
	}

	public JSONException(final org.codehaus.jettison.json.JSONException cause) {
		super(cause);
	}
}
