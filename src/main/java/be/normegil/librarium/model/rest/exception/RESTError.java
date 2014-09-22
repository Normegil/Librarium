package be.normegil.librarium.model.rest.exception;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.rest.HttpStatus;
import be.normegil.librarium.util.parser.CsvSchema;
import be.normegil.librarium.util.parser.adapter.jaxb.LocalDateTimeJAXBAdapter;
import be.normegil.librarium.util.parser.adapter.json.LocalDateTimeJsonDeserializer;
import be.normegil.librarium.util.parser.adapter.json.LocalDateTimeJsonSerializer;
import be.normegil.librarium.validation.constraint.NotEmpty;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@CsvSchema(readOnly = true, columns = {
		"code",
		"httpStatus",
		"message",
		"developerMessage",
		"moreInfoUrl",
		"time"
})
public class RESTError {

	@NotNull
	private HttpStatus httpStatus;
	@Min(1)
	private int code;
	@NotEmpty
	private String message;
	@NotEmpty
	private String developerMessage;
	@NotNull
	@Valid
	private URL moreInfoUrl;

	@JsonSerialize(using = LocalDateTimeJsonSerializer.class)
	@JsonDeserialize(using = LocalDateTimeJsonDeserializer.class)
	@XmlJavaTypeAdapter(LocalDateTimeJAXBAdapter.class)
	private LocalDateTime time;

	private RESTError(@NotNull @Valid final Builder builder) {
		code = builder.code;
		httpStatus = builder.status;
		message = builder.message;
		developerMessage = builder.developerMessage;
		moreInfoUrl = builder.moreInfoUrl;
		time = builder.time;
	}

	public RESTError(@NotNull @Valid final RESTError error) {
		code = error.getCode();
		httpStatus = error.getHttpStatus();
		message = error.getMessage();
		developerMessage = error.getDeveloperMessage();
		moreInfoUrl = error.getMoreInfoURL();
		time = error.getTime();
	}

	// For Parsers
	@SuppressWarnings(WarningTypes.UNUSED)
	private RESTError() {
	}

	public static Builder builder() {
		return new Builder();
	}

	public RESTError withTime(@NotNull @Valid LocalDateTime time) {
		RESTError error = new RESTError(this);
		error.time = time;
		return error;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
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

	public LocalDateTime getTime() {
		return time;
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
				.append(this.time, rhs.time)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(code)
				.append(time)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ApplicationProperties.TO_STRING_STYLE)
				.append("httpStatus", httpStatus)
				.append("code", code)
				.append("message", message)
				.append("developerMessage", developerMessage)
				.append("moreInfoUrl", moreInfoUrl)
				.append("time", time)
				.toString();
	}

	public static class Builder {
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
		private LocalDateTime time;

		public Builder from(@NotNull @Valid RESTError error) {
			code = error.getCode();
			status = error.getHttpStatus();
			message = error.getMessage();
			developerMessage = error.getDeveloperMessage();
			moreInfoUrl = error.getMoreInfoURL();
			time = error.getTime();
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

		public Builder setTime(@NotNull final LocalDateTime time) {
			this.time = time;
			return this;
		}

		public
		@Valid
		RESTError build() {
			return new RESTError(this);
		}
	}
}
