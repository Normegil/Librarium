package be.normegil.librarium.validation.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Duration;

public class PositiveDurationValidator implements ConstraintValidator<Positive, Duration> {
	@Override
	public void initialize(final Positive constraintAnnotation) {
	}

	@Override
	public boolean isValid(final Duration value, final ConstraintValidatorContext context) {
		return value == null || !value.isNegative();
	}
}
