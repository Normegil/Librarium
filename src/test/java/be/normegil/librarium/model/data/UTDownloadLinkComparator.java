package be.normegil.librarium.model.data;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataComparableTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTDownloadLinkComparator extends AbstractDataComparableTest<DownloadLink> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<DownloadLink> FACTORY = FactoryRepository.get(DownloadLink.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BaseMedia> BASE_MEDIA_FACTORY = FactoryRepository.get(BaseMedia.class);

	@Override
	protected DownloadLink getNewEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected int compare(final DownloadLink entity1, final DownloadLink entity2) {
		return entity1.compareTo(entity2);
	}

	@Override
	public void testEquality() throws Exception {
		DownloadLink entity = getEntity();
		DownloadLink copy = new DownloadLink(entity);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
		assertEquals(compare(entity, copy), entity.compareTo(copy));
	}

	@Test
	public void testTitle_First() throws Exception {
		DownloadLink entity = getEntity();
		DownloadLink copy = new DownloadLink(entity);
		copy.setTitle(entity.getTitle() + 1);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testTitle_Second() throws Exception {
		DownloadLink entity = getEntity();
		DownloadLink copy = new DownloadLink(entity);
		copy.setTitle(entity.getTitle() + 1);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}

	@Test
	public void testDescription_Equality() throws Exception {
		DownloadLink entity = getEntity();
		DownloadLink copy = new DownloadLink(entity);
		copy.setDescription(entity.getDescription() + 1);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
	}

	@Test
	public void testLink_First() throws Exception {
		DownloadLink entity = getEntity();
		DownloadLink copy = new DownloadLink(entity);
		copy.setLink(URL_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testLink_Second() throws Exception {
		DownloadLink entity = getEntity();
		DownloadLink copy = new DownloadLink(entity);
		copy.setLink(URL_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}

	@Test
	public void testMedia_First() throws Exception {
		DownloadLink entity = getEntity();
		DownloadLink copy = new DownloadLink(entity);
		copy.setMedia(BASE_MEDIA_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testMedia_Second() throws Exception {
		DownloadLink entity = getEntity();
		DownloadLink copy = new DownloadLink(entity);
		copy.setMedia(BASE_MEDIA_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}
}
