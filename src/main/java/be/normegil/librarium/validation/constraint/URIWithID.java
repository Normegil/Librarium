package be.normegil.librarium.validation.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({
		ElementType.FIELD,
		ElementType.PARAMETER
})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = URIWithIDValidator.class)
@NotNull
public @interface URIWithID {

	String message() default
			"Given argument is missing an id parameter";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
