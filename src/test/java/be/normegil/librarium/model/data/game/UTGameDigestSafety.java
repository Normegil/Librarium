package be.normegil.librarium.model.data.game;

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
import java.lang.reflect.Method;
import java.net.URI;

public class UTGameDigestSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> GAME_FACTORY = FactoryRepository.get(Game.class);
	private static final String URI_ADDRESS = "http://localhost:8080/";
	private static final ClassWrapper<Game.GameDigest> CLASS = new ClassWrapper<>(Game.GameDigest.class);
	private static final Method FROM_BASE_METHOD = CLASS.getMethod("fromBase", URI.class, Game.class);
	private static final Method TO_BASE_METHOD = CLASS.getMethod("toBase", Game.Init.class);
	private Game.GameDigest entity;

	@Before
	public void setUp() throws Exception {
		entity = new Game.GameDigest();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFromBase_NullURI() throws Exception {
		Validator.validate(entity, FROM_BASE_METHOD, null, GAME_FACTORY.getNext());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFromBase_NullEntity() throws Exception {
		Validator.validate(entity, FROM_BASE_METHOD, URI.create(URI_ADDRESS), null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFromBase_NullEntityID() throws Exception {
		Validator.validate(entity, FROM_BASE_METHOD, URI.create(URI_ADDRESS), new FakeEntity());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testToBase_Null() throws Exception {
		Validator.validate(entity, TO_BASE_METHOD, new Object[]{null});
	}

}
