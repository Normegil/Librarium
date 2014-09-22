package be.normegil.librarium.util;

import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.io.InputStream;

public class UTInputStreamHelperSafety {

	private static final ClassWrapper<InputStreamHelper> CLASS = new ClassWrapper<>(InputStreamHelper.class);
	private InputStreamHelper entity;

	@Before
	public void setUp() throws Exception {
		entity = new InputStreamHelper();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testToString_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("toString", InputStream.class), new Object[]{null});
	}
}