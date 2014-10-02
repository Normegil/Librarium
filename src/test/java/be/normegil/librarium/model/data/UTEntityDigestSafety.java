package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.model.data.fake.FakeEntity;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.net.URI;

public class UTEntityDigestSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Entity> ENTITY_FACTORY = FactoryRepository.get(Entity.class);
	private static final String URI_ADDRESS = "http://localhost:8080/";
	private static final ClassWrapper<Entity.EntityDigest> CLASS = new ClassWrapper<>(Entity.EntityDigest.class);
	private static final java.lang.reflect.Method FROM_BASE_METHOD = CLASS.getMethod("fromBase", URI.class, Entity.class);
	private Entity.EntityDigest entity;

	@Before
	public void setUp() throws Exception {
		entity = new Entity.EntityDigest();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFromBase_NullURI() throws Exception {
		Validator.validate(entity, FROM_BASE_METHOD, null, ENTITY_FACTORY.getNext());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFromBase_NullEntity() throws Exception {
		Validator.validate(entity, FROM_BASE_METHOD, URI.create(URI_ADDRESS), null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFromBase_NullEntityID() throws Exception {
		Validator.validate(entity, FROM_BASE_METHOD, URI.create(URI_ADDRESS), new FakeEntity());
	}
}