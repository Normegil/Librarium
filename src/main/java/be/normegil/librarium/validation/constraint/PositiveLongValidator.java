package be.normegil.librarium.validation.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PositiveLongValidator implements ConstraintValidator<Positive, Long> {
	@Override
	public void initialize(final Positive constraintAnnotation) {
	}

	@Override
	public boolean isValid(final Long value, final ConstraintValidatorContext context) {
		return value != null && value >= 0;
	}
}
