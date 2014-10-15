package be.normegil.librarium.util;

import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.model.data.Entity;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;

public class UTXSDUtil_Safety {

	private static final ClassWrapper<XSDUtil> CLASS = new ClassWrapper<>(XSDUtil.class);
	private XSDUtil entity;

	@Before
	public void setUp() throws Exception {
		entity = new XSDUtil();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testGetSchema_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("getSchema", Class.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testGetSchema_NoAnnotation() throws Exception {
		Validator.validate(entity, CLASS.getMethod("getSchema", Class.class), Entity.class);
	}
}
