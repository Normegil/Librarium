package be.normegil.librarium.model.rest;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTCollectionResource {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<CollectionResource> FACTORY = FactoryRepository.get(CollectionResource.class);
	private CollectionResource entity;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getDefault();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testCopyConstructor() throws Exception {
		CollectionResource copy = new CollectionResource(entity);
		assertEquals(entity, copy);
	}
}
