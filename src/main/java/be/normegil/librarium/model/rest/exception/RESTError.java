package be.normegil.librarium.model.rest.exception;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.rest.HttpStatus;
import be.normegil.librarium.validation.constraint.NotEmpty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class RESTError {

	@NotNull
	private final HttpStatus status;
	@Min(1)
	private final int code;
	@NotEmpty
	private final String message;
	@NotEmpty
	private final String developerMessage;
	@NotNull
	@Valid
	private final URL moreInfoUrl;
	private Throwable throwable;

	private RESTError(@NotNull @Valid final Builder builder) {
		code = builder.code;
		status = builder.status;
		message = builder.message;
		developerMessage = builder.developerMessage;
		moreInfoUrl = builder.moreInfoUrl;
		throwable = builder.throwable;
	}

	public RESTError(@NotNull @Valid final RESTError error) {
		code = error.getCode();
		status = error.getStatus();
		message = error.getMessage();
		developerMessage = error.getDeveloperMessage();
		moreInfoUrl = error.getMoreInfoURL();
		throwable = error.getThrowable();
	}

	public static Builder builder() {
		return new Builder();
	}

	public RESTError withThrowable(@NotNull @Valid Throwable throwable) {
		RESTError error = new RESTError(this);
		error.throwable = throwable;
		return error;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public URL getMoreInfoURL() {
		return moreInfoUrl;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		RESTError rhs = (RESTError) obj;
		return new EqualsBuilder()
				.append(this.code, rhs.code)
				.append(this.throwable, rhs.throwable)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(code)
				.append(throwable)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ApplicationProperties.TO_STRING_STYLE)
				.append("status", status)
				.append("code", code)
				.append("message", message)
				.append("developerMessage", developerMessage)
				.append("moreInfoUrl", moreInfoUrl)
				.append("throwable", throwable)
				.toString();
	}

	public static class Builder {
		private Throwable throwable;
		@NotNull
		private HttpStatus status;
		@Min(1)
		private int code;
		@NotEmpty
		private String message;
		@NotEmpty
		private String developerMessage;
		@NotNull
		private URL moreInfoUrl;

		public Builder from(@NotNull @Valid RESTError error) {
			code = error.getCode();
			status = error.getStatus();
			message = error.getMessage();
			developerMessage = error.getDeveloperMessage();
			moreInfoUrl = error.getMoreInfoURL();
			throwable = error.getThrowable();
			return this;
		}

		public Builder setCode(@Min(1) final int code) {
			this.code = code;
			return this;
		}

		public Builder setHttpStatus(@NotNull final HttpStatus status) {
			this.status = status;
			return this;
		}

		public Builder setMessage(@NotEmpty final String message) {
			this.message = message;
			return this;
		}

		public Builder setDeveloperMessage(@NotEmpty final String developerMessage) {
			this.developerMessage = developerMessage;
			return this;
		}

		public Builder setMoreInfoURL(@NotNull @Valid final URL moreInfoUrl) {
			this.moreInfoUrl = moreInfoUrl;
			return this;
		}

		public Builder setThrowable(@NotNull final Throwable throwable) {
			this.throwable = throwable;
			return this;
		}

		public
		@Valid
		RESTError build() {
			return new RESTError(this);
		}
	}
}
