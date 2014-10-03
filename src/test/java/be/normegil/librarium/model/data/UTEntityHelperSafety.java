package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class UTEntityHelperSafety {

	public static final String INVALID_URI = "http://localhost:8080/rest/anythingNotUUID";
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Entity> ENTITY_FACTORY = FactoryRepository.get(Entity.class);
	private static final ClassWrapper<Entity.Helper> CLASS = new ClassWrapper<>(Entity.Helper.class);
	private static final Method CONVERT_TO_URL_METHOD = CLASS.getMethod("convertToURLs", List.class, URL.class);
	private static final Method GET_ID_FROM_RESTURI_METHOD = CLASS.getMethod("getIdFromRESTURI", URI.class);
	private static final Method SET_ID_FROM_DIGEST_METHOD = CLASS.getMethod("setIdFromDigest", Entity.EntityDigest.class, Entity.class);
	private Entity.Helper entity;

	@Before
	public void setUp() throws Exception {
		entity = Entity.helper();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testConvertToURLs_NullList() throws Exception {
		Validator.validate(entity, CONVERT_TO_URL_METHOD, null, URL_FACTORY.getNext());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testConvertToURLs_NullURL() throws Exception {
		Validator.validate(entity, CONVERT_TO_URL_METHOD, new ArrayList<>(), null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testGetIdFromRESTURI_Null() throws Exception {
		Validator.validate(entity, GET_ID_FROM_RESTURI_METHOD, new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testGetIdFromRESTURI_InvalidURI() throws Exception {
		Validator.validate(entity, GET_ID_FROM_RESTURI_METHOD, URI.create(INVALID_URI));
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetIdFromDigest_FirstNull() throws Exception {
		Validator.validate(entity, SET_ID_FROM_DIGEST_METHOD, null, ENTITY_FACTORY.getNext());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetIdFromDigest_SecondNull() throws Exception {
		Validator.validate(entity, SET_ID_FROM_DIGEST_METHOD, new Entity.EntityDigest(), null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetIdFromDigest_InvalidURI() throws Exception {
		Entity e = ENTITY_FACTORY.getNext();
		Entity.EntityDigest digest = new Entity.EntityDigest();
		digest.href = URI.create(INVALID_URI);
		Validator.validate(entity, SET_ID_FROM_DIGEST_METHOD, digest, e);
	}
}
