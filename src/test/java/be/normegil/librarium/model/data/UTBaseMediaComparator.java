package be.normegil.librarium.model.data;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.data.fake.FakeBaseMedia;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.AbstractDataComparableTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTBaseMediaComparator extends AbstractDataComparableTest<BaseMedia> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BaseMedia> FACTORY = FactoryRepository.get(BaseMedia.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<DownloadLink> DOWNLOAD_LINK_FACTORY = FactoryRepository.get(DownloadLink.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> GAME_FACTORY = FactoryRepository.get(Game.class);
	private static final String ALTERNATIVE_TAG = "z200";

	@Override
	protected BaseMedia getNewEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected int compare(final BaseMedia entity1, final BaseMedia entity2) {
		return entity1.compareTo(entity2);
	}

	@Override
	public void testEquality() throws Exception {
		BaseMedia entity = getEntity();
		BaseMedia copy = new FakeBaseMedia(entity);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
		assertEquals(compare(entity, copy), entity.compareTo(copy));
	}

	@Test
	public void testClass_First() throws Exception {
		BaseMedia entity = getEntity();
		Game game = GAME_FACTORY.getNext();
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, game)));
	}

	@Test
	public void testClass_Second() throws Exception {
		BaseMedia entity = getEntity();
		Game game = GAME_FACTORY.getNext();
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(game, entity)));
	}

	@Test
	public void testTitle_First() throws Exception {
		BaseMedia entity = getEntity();
		BaseMedia copy = new FakeBaseMedia(entity);
		copy.setTitle(entity.getTitle() + 1);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testTitle_Second() throws Exception {
		BaseMedia entity = getEntity();
		BaseMedia copy = new FakeBaseMedia(entity);
		copy.setTitle(entity.getTitle() + 1);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}

	@Test
	public void testDescription_First() throws Exception {
		BaseMedia entity = getEntity();
		BaseMedia copy = new FakeBaseMedia(entity);
		copy.setDescription(entity.getDescription() + 1);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testDescription_Second() throws Exception {
		BaseMedia entity = getEntity();
		BaseMedia copy = new FakeBaseMedia(entity);
		copy.setDescription(entity.getDescription() + 1);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}

	@Test
	public void testTag_First() throws Exception {
		BaseMedia entity = getEntity();
		BaseMedia copy = new FakeBaseMedia(entity);
		copy.addTag(ALTERNATIVE_TAG);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testTag_Second() throws Exception {
		BaseMedia entity = getEntity();
		BaseMedia copy = new FakeBaseMedia(entity);
		copy.addTag(ALTERNATIVE_TAG);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}

	@Test
	public void testOfficialWebsite_First() throws Exception {
		BaseMedia entity = getEntity();
		BaseMedia copy = new FakeBaseMedia(entity);
		copy.setOfficialWebsite(URL_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testOfficialWebsite_Second() throws Exception {
		BaseMedia entity = getEntity();
		BaseMedia copy = new FakeBaseMedia(entity);
		copy.setOfficialWebsite(URL_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}

	@Test
	public void testWikipediaPage_First() throws Exception {
		BaseMedia entity = getEntity();
		BaseMedia copy = new FakeBaseMedia(entity);
		copy.setWikipediaPage(URL_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testWikipediaPage_Second() throws Exception {
		BaseMedia entity = getEntity();
		BaseMedia copy = new FakeBaseMedia(entity);
		copy.setWikipediaPage(URL_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}

	@Test
	public void testStore_First() throws Exception {
		BaseMedia entity = getEntity();
		BaseMedia copy = new FakeBaseMedia(entity);
		copy.addStore(URL_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testStore_Second() throws Exception {
		BaseMedia entity = getEntity();
		BaseMedia copy = new FakeBaseMedia(entity);
		copy.addStore(URL_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}

	@Test
	public void testDownloadLink_Equality() throws Exception {
		BaseMedia entity = getEntity();
		BaseMedia copy = new FakeBaseMedia(entity);
		copy.addDownloadLink(DOWNLOAD_LINK_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
	}
}
