package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataEqualityTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTMangaEquality extends AbstractDataEqualityTest<Manga> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Manga> FACTORY = FactoryRepository.get(Manga.class);

	@Override
	protected Manga getNewEntity() {
		return FACTORY.getNew();
	}

	@Test
	public void testUnchanged() throws Exception {
		Manga entity = getEntity();
		Manga copy = new Manga(entity);
		assertEquals(entity, copy);
	}
}