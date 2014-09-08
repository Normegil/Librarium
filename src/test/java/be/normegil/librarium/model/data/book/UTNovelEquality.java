package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataEqualityTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTNovelEquality extends AbstractDataEqualityTest<Novel> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Novel> FACTORY = FactoryRepository.get(Novel.class);

	@Override
	protected Novel getNewEntity() {
		return FACTORY.getNew();
	}

	@Test
	public void testUnchanged() throws Exception {
		Novel entity = getEntity();
		Novel copy = new Novel(entity);
		assertEquals(entity, copy);
	}
}