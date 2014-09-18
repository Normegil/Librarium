package be.normegil.librarium.validation.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HttpStatusValidator implements ConstraintValidator<HttpStatus, Integer>{
	@Override
	public void initialize(final HttpStatus constraintAnnotation) {
	}

	@Override
	public boolean isValid(final Integer value, final ConstraintValidatorContext context) {
		
	}
}
