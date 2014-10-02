package be.normegil.librarium.validation.constraint;

import be.normegil.librarium.Constants;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class URIAsStringWithIDValidator implements ConstraintValidator<URIWithID, String> {
	@Override
	public void initialize(final URIWithID constraintAnnotation) {
	}

	@Override
	public boolean isValid(final String value, final ConstraintValidatorContext context) {
		String id = StringUtils.substringAfterLast(value, Constants.URL.PATH_SEPARATOR);
		try {
			UUID uuid = UUID.fromString(id);
			return uuid != null;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
}
