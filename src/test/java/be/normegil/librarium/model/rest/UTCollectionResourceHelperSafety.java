package be.normegil.librarium.model.rest;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;

public class UTCollectionResourceHelperSafety {

	private static final ClassWrapper<CollectionResource.Helper> CLASS = new ClassWrapper<>(CollectionResource.Helper.class);
	private CollectionResource.Helper entity;

	@Before
	public void setUp() throws Exception {
		entity = CollectionResource.helper();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testGetCollectionURL_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("getCollectionURL", URL.class, long.class, int.class), null, 0L, ApplicationProperties.REST.DEFAULT_LIMIT);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testGetBaseURL_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("getBaseURL", URL.class), new Object[]{null});
	}
}
