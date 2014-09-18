package be.normegil.librarium.model.rest.exception;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTRESTError {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<RESTError> FACTORY = FactoryRepository.get(RESTError.class);
	private RESTError entity;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getNew();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testCopyConstructor() throws Exception {
		RESTError copy = new RESTError(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testWithThrowable() throws Exception {
		Throwable throwable = new IllegalArgumentException();
		RESTError copy = entity.withThrowable(throwable);
		assertEquals(throwable, copy.getThrowable());
	}

	@Test
	public void testWithThrowable_Immutable() throws Exception {
		Throwable originalThrowable = entity.getThrowable();
		entity.withThrowable(new IllegalArgumentException());
		assertEquals(originalThrowable, entity.getThrowable());
	}
}
