package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.data.fake.FakeBaseMedia;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataEqualityTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTBaseMediaEquality extends AbstractDataEqualityTest<BaseMedia> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BaseMedia> FACTORY = FactoryRepository.get(BaseMedia.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<DownloadLink> DOWNLOAD_LINK_FACTORY = FactoryRepository.get(DownloadLink.class);
	private static final String ALTERNATIVE_TAG = "AlternativeTag";

	@Override
	protected BaseMedia getNewEntity() {
		return FACTORY.getNew(true);
	}

	@Test
	public void testUnchanged() throws Exception {
		BaseMedia entity = getEntity();
		BaseMedia copy = new FakeBaseMedia(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentTitle() throws Exception {
		BaseMedia entity = getEntity();
		BaseMedia copy = new FakeBaseMedia(entity);
		entity.setTitle(entity.getTitle() + 1);
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentDescription() throws Exception {
		BaseMedia entity = getEntity();
		BaseMedia copy = new FakeBaseMedia(entity);
		entity.setDescription(entity.getDescription() + 1);
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentTags() throws Exception {
		BaseMedia entity = getEntity();
		BaseMedia copy = new FakeBaseMedia(entity);
		entity.addTag(ALTERNATIVE_TAG);
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentOfficialWebsite() throws Exception {
		BaseMedia entity = getEntity();
		BaseMedia copy = new FakeBaseMedia(entity);
		entity.setOfficialWebsite(URL_FACTORY.getNext(true));
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentWikipediaPage() throws Exception {
		BaseMedia entity = getEntity();
		BaseMedia copy = new FakeBaseMedia(entity);
		entity.setWikipediaPage(URL_FACTORY.getNext(true));
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentStore() throws Exception {
		BaseMedia entity = getEntity();
		BaseMedia copy = new FakeBaseMedia(entity);
		entity.addStore(URL_FACTORY.getNext(true));
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentDownloadLink() throws Exception {
		BaseMedia entity = getEntity();
		BaseMedia copy = new FakeBaseMedia(entity);
		entity.addDownloadLink(DOWNLOAD_LINK_FACTORY.getNext(true));
		assertEquals(entity, copy);
	}
}