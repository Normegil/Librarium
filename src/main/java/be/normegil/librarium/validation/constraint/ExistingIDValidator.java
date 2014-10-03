package be.normegil.librarium.validation.constraint;

import be.normegil.librarium.model.data.Entity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistingIDValidator implements ConstraintValidator<ExistingID, Entity> {
	@Override
	public void initialize(final ExistingID constraintAnnotation) {
	}

	@Override
	public boolean isValid(final Entity value, final ConstraintValidatorContext context) {
		return value == null || value.getId() != null;
	}
}
