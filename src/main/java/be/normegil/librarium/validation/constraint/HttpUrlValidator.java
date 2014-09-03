package be.normegil.librarium.validation.constraint;

import be.normegil.librarium.libraries.URL;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HttpUrlValidator implements ConstraintValidator<HttpUrl, URL> {

	public static final String PROTOCOL = "Http";

	@Override
	public void initialize(final HttpUrl constraintAnnotation) {
	}

	@Override
	public boolean isValid(final URL value, final ConstraintValidatorContext context) {
		return value != null && StringUtils.equalsIgnoreCase(PROTOCOL, value.getProtocol());
	}
}
