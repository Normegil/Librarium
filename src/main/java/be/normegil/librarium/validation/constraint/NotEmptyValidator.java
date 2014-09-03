package be.normegil.librarium.validation.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyValidator implements ConstraintValidator<NotEmpty, String> {
	@Override
	public void initialize(final NotEmpty notEmpty) {
	}

	@Override
	public boolean isValid(final String s, final ConstraintValidatorContext constraintValidatorContext) {
		return s != null && !s.isEmpty();
	}
}
