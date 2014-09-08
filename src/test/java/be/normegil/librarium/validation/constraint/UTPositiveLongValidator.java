package be.normegil.librarium.validation.constraint;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UTPositiveLongValidator {

	private static final long POSITIVE = 1L;
	private static final long ZERO = 0L;
	private static final long NEGATIVE = -1L;
	private ConstraintValidatorContextImpl context = new ConstraintValidatorContextImpl(null, null, null);
	private PositiveLongValidator entity = new PositiveLongValidator();

	@Test
	public void testNull() throws Exception {
		assertFalse(entity.isValid(null, context));
	}

	@Test
	public void testNegative() throws Exception {
		assertFalse(entity.isValid(NEGATIVE, context));
	}

	@Test
	public void testZero() throws Exception {
		assertTrue(entity.isValid(ZERO, context));
	}

	@Test
	public void testPositive() throws Exception {
		assertTrue(entity.isValid(POSITIVE, context));
	}
}
