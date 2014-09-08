package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataEqualityTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTComicEquality extends AbstractDataEqualityTest<Comic> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Comic> FACTORY = FactoryRepository.get(Comic.class);

	@Override
	protected Comic getNewEntity() {
		return FACTORY.getNew();
	}

	@Test
	public void testUnchanged() throws Exception {
		Comic entity = getEntity();
		Comic copy = new Comic(entity);
		assertEquals(entity, copy);
	}
}