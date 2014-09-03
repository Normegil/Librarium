package be.normegil.librarium.validation.constraint;

import be.normegil.librarium.validation.constraint.PositiveDurationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({
		ElementType.FIELD,
		ElementType.PARAMETER
})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PositiveDurationValidator.class)
public @interface Positive {

	String message() default
			"Given argument cannot be negative";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
