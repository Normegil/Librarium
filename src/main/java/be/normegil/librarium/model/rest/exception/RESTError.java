package be.normegil.librarium.model.rest.exception;

import org.apache.http.HttpStatus;

public class RESTError {

	private final HttpStatus status;
	private final int code;
	private final String message;
	private final String developerMessage;
	private final String moreInfoUrl;
	private Throwable throwable;


	private RESTError(final Builder builder) {
		code = builder.code;
		status = builder.status;
		message = builder.message;
		developerMessage = builder.developerMessage;
		moreInfoUrl = builder.moreInfoUrl;
		throwable = builder.throwable;
	}

	public RESTError(final RESTError error) {
		code = error.code;
		status = error.status;
		message = error.message;
		developerMessage = error.developerMessage;
		moreInfoUrl = error.moreInfoUrl;
		throwable = error.throwable;
	}

	public RESTError withThrowable(Throwable throwable) {
		RESTError error = new RESTError(this);
		error.throwable = throwable;
		return error;
	}

	public static class Builder {
		private Throwable throwable;
		private int code;
		private String message;
		private String developerMessage;
		private String moreInfoUrl;
		private HttpStatus status;

		public Builder from(RESTError error) {
			return this;
		}

		public Builder setCode(final int code) {
			this.code = code;
			return this;
		}

		public Builder setCode(final HttpStatus status) {
			this.status = status;
			return this;
		}

		public Builder setMessage(final String message) {
			this.message = message;
			return this;
		}

		public Builder setDeveloperMessage(final String developerMessage) {
			this.developerMessage = developerMessage;
			return this;
		}

		public Builder setMoreInfoUrl(final String moreInfoUrl) {
			this.moreInfoUrl = moreInfoUrl;
			return this;
		}

		public Builder setThrowable(final Throwable throwable) {
			this.throwable = throwable;
			return this;
		}

		public RESTError build() {
			return new RESTError(this);
		}
	}
}
