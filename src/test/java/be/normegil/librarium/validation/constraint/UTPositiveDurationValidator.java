package be.normegil.librarium.validation.constraint;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UTPositiveDurationValidator {

	private PositiveDurationValidator entity = new PositiveDurationValidator();
	private ConstraintValidatorContextImpl context = new ConstraintValidatorContextImpl(null, null, null);

	@Test
	public void testNull() throws Exception {
		assertTrue(entity.isValid(null, context));
	}

	@Test
	public void testNegative() throws Exception {
		assertFalse(entity.isValid(Duration.ofMinutes(-1), context));
	}

	@Test
	public void testZero() throws Exception {
		assertTrue(entity.isValid(Duration.ofMinutes(0), context));
	}

	@Test
	public void testPositive() throws Exception {
		assertTrue(entity.isValid(Duration.ofMinutes(1), context));
	}
}
