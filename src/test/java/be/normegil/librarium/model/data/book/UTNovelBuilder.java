package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTNovelBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Novel> NOVEL_FACTORY = FactoryRepository.get(Novel.class);
	private Novel.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = Novel.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFrom() throws Exception {
		Novel novel = NOVEL_FACTORY.getNext();
		Novel copy = entity.from(novel).build();
		assertEquals(novel, copy);
	}
}