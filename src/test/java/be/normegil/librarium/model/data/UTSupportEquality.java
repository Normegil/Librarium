package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataEqualityTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTSupportEquality extends AbstractDataEqualityTest<Support> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Support> FACTORY = FactoryRepository.get(Support.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> MEDIA_FACTORY = FactoryRepository.get(Media.class);

	@Override
	protected Support getNewEntity() {
		return FACTORY.getDefault();
	}

	@Test
	public void testUnchanged() throws Exception {
		Support entity = getEntity();
		Support copy = new Support(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentWikipediaPage() throws Exception {
		Support entity = getEntity();
		Support copy = new Support(entity);
		entity.setWikipediaPage(URL_FACTORY.getNew());
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentMedia() throws Exception {
		Support entity = getEntity();
		Support copy = new Support(entity);
		entity.addMedia(MEDIA_FACTORY.getNew());
		assertEquals(entity, copy);
	}
}