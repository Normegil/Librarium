package be.normegil.librarium.validation.constraint;

import be.normegil.librarium.util.DateHelper;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UTValidDateFormatValidator {

	private static final String EMPTY_DATE = "";
	private static final String INVALID_DATE_FORMAT = "2014-08-02";
	private ValidDateFormatValidator entity = new ValidDateFormatValidator();
	private ConstraintValidatorContextImpl context = new ConstraintValidatorContextImpl(null, null, null);

	@Test
	public void testNull() throws Exception {
		assertFalse(entity.isValid(null, context));
	}

	@Test
	public void testEmpty() throws Exception {
		assertFalse(entity.isValid(EMPTY_DATE, context));
	}

	@Test
	public void testInvalid() throws Exception {
		assertFalse(entity.isValid(INVALID_DATE_FORMAT, context));
	}

	@Test
	public void testValid() throws Exception {
		LocalDate now = LocalDate.now();
		assertTrue(entity.isValid(new DateHelper().format(now), context));
	}
}
