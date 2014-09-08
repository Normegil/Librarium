package be.normegil.librarium.model.data.book;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataComparableTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTMangaComparator extends AbstractDataComparableTest<Manga> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Manga> FACTORY = FactoryRepository.get(Manga.class);

	@Override
	protected Manga getNewEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected int compare(final Manga entity1, final Manga entity2) {
		return entity1.compareTo(entity2);
	}

	@Override
	public void testEquality() throws Exception {
		Manga entity = getEntity();
		Manga copy = new Manga(entity);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
		assertEquals(compare(entity, copy), entity.compareTo(copy));
	}
}
