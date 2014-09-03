package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTBD {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BD> FACTORY = FactoryRepository.get(BD.class);
	private BD entity;

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
		BD copy = new BD(entity);
		assertEquals(entity, copy);
	}
}
