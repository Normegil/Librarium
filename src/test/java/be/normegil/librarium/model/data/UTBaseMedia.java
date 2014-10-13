package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.data.fake.FakeBaseMedia;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTBaseMedia {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BaseMedia> FACTORY = FactoryRepository.get(BaseMedia.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<DownloadLink> DOWNLOAD_LINK_FACTORY = FactoryRepository.get(DownloadLink.class);
	private static final String TITLE = "Title";
	private static final String DESCRIPTION = "Description";
	private static final String ALTERNATIVE_TAG = "AlternativeTag";
	private BaseMedia entity;
	private Collection<String> tags;
	private Collection<URL> stores;
	private Collection<DownloadLink> downloadLinks;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getDefault();
		tags = entity.getTags();
		stores = entity.getStores();
		downloadLinks = entity.getDownloadLinks();
	}

	@After
	public void tearDown() throws Exception {
		downloadLinks = null;
		stores = null;
		tags = null;
		entity = null;
	}

	@Test
	public void testCopyConstructor() throws Exception {
		BaseMedia copy = new FakeBaseMedia(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testSetTitle() throws Exception {
		entity.setTitle(TITLE);
		assertEquals(TITLE, entity.getTitle());
	}

	@Test
	public void testSetDescription() throws Exception {
		entity.setDescription(DESCRIPTION);
		assertEquals(DESCRIPTION, entity.getDescription());
	}

	@Test
	public void testAddAllTags() throws Exception {
		HashSet<String> toAdd = new HashSet<>();
		toAdd.add(UTBaseMedia.ALTERNATIVE_TAG);
		toAdd.add(UTBaseMedia.ALTERNATIVE_TAG + 1);
		toAdd.add(UTBaseMedia.ALTERNATIVE_TAG + 2);

		tags.addAll(toAdd);
		entity.addAllTags(toAdd);
		assertEquals(tags, entity.getTags());
	}

	@Test
	public void testAddTag() throws Exception {
		String toAdd = UTBaseMedia.ALTERNATIVE_TAG;
		tags.add(toAdd);
		entity.addTag(toAdd);
		assertEquals(tags, entity.getTags());
	}

	@Test
	public void testRemoveAllTags() throws Exception {
		String base = UTBaseMedia.ALTERNATIVE_TAG;
		String second = UTBaseMedia.ALTERNATIVE_TAG + 1;
		String third = UTBaseMedia.ALTERNATIVE_TAG + 2;

		Collection<String> toAdd = new HashSet<>();
		toAdd.add(base);
		toAdd.add(second);
		toAdd.add(third);

		HashSet<String> toRemove = new HashSet<>();
		toRemove.add(second);
		toRemove.add(third);

		tags.addAll(toAdd);
		entity.addAllTags(toAdd);

		tags.removeAll(toRemove);
		entity.removeAllTags(toRemove);

		assertEquals(tags, entity.getTags());
	}

	@Test
	public void testRemoveTag() throws Exception {
		String toRemove = entity.getTags().iterator().next();
		tags.remove(toRemove);
		entity.removeTag(toRemove);

		assertEquals(tags, entity.getTags());
	}

	@Test
	public void testClearTags() throws Exception {
		entity.addTag(ALTERNATIVE_TAG);
		entity.clearTags();
		assertTrue(entity.getTags().isEmpty());
	}

	@Test
	public void testSetOfficialWebsite() throws Exception {
		URL url = URL_FACTORY.getNew();
		entity.setOfficialWebsite(url);
		assertEquals(url, entity.getOfficialWebsite());
	}

	@Test
	public void testSetWikipediaPage() throws Exception {
		URL url = URL_FACTORY.getNew();
		entity.setWikipediaPage(url);
		assertEquals(url, entity.getWikipediaPage());
	}

	@Test
	public void testAddAllStores() throws Exception {
		Collection<URL> toAdd = new HashSet<>();
		toAdd.add(URL_FACTORY.getNew());
		toAdd.add(URL_FACTORY.getNew());
		toAdd.add(URL_FACTORY.getNew());

		stores.addAll(toAdd);
		entity.addAllStores(toAdd);
		assertEquals(stores, entity.getStores());
	}

	@Test
	public void testAddStore() throws Exception {
		URL toAdd = URL_FACTORY.getNew();
		stores.add(toAdd);
		entity.addStore(toAdd);
		assertEquals(stores, entity.getStores());
	}

	@Test
	public void testRemoveAllStores() throws Exception {
		URL base = URL_FACTORY.getNew();
		URL second = URL_FACTORY.getNew();
		URL third = URL_FACTORY.getNew();

		Collection<URL> toAdd = new HashSet<>();
		toAdd.add(base);
		toAdd.add(second);
		toAdd.add(third);

		Collection<URL> toRemove = new HashSet<>();
		toRemove.add(second);
		toRemove.add(third);

		stores.addAll(toAdd);
		entity.addAllStores(toAdd);

		stores.removeAll(toRemove);
		entity.removeAllStores(toRemove);

		assertEquals(stores, entity.getStores());
	}

	@Test
	public void testRemoveStore() throws Exception {
		URL toRemove = entity.getStores().iterator().next();
		stores.remove(toRemove);
		entity.removeStore(toRemove);

		assertEquals(stores, entity.getStores());
	}

	@Test
	public void testClearStores() throws Exception {
		entity.addStore(URL_FACTORY.getNew());
		entity.clearStores();
		assertTrue(entity.getStores().isEmpty());
	}

	@Test
	public void testAddAllDownloadLinks() throws Exception {
		Collection<DownloadLink> toAdd = new HashSet<>();
		toAdd.add(DOWNLOAD_LINK_FACTORY.getNew());
		toAdd.add(DOWNLOAD_LINK_FACTORY.getNew());
		toAdd.add(DOWNLOAD_LINK_FACTORY.getNew());

		downloadLinks.addAll(toAdd);
		entity.addAllDownloadLinks(toAdd);
		assertEquals(downloadLinks, entity.getDownloadLinks());
	}

	@Test
	public void testAddDownloadLink() throws Exception {
		DownloadLink toAdd = DOWNLOAD_LINK_FACTORY.getNew();
		downloadLinks.add(toAdd);
		entity.addDownloadLink(toAdd);
		assertEquals(downloadLinks, entity.getDownloadLinks());
	}

	@Test
	public void testRemoveAllDownloadLinks() throws Exception {
		DownloadLink base = DOWNLOAD_LINK_FACTORY.getNew();
		DownloadLink second = DOWNLOAD_LINK_FACTORY.getNew();
		DownloadLink third = DOWNLOAD_LINK_FACTORY.getNew();

		Collection<DownloadLink> toAdd = new HashSet<>();
		toAdd.add(base);
		toAdd.add(second);
		toAdd.add(third);

		Collection<DownloadLink> toRemove = new HashSet<>();
		toRemove.add(second);
		toRemove.add(third);

		downloadLinks.addAll(toAdd);
		entity.addAllDownloadLinks(toAdd);

		downloadLinks.removeAll(toRemove);
		entity.removeAllDownloadLinks(toRemove);

		assertEquals(downloadLinks, entity.getDownloadLinks());
	}

	@Test
	public void testRemoveDownloadLink() throws Exception {
		DownloadLink toRemove = entity.getDownloadLinks().iterator().next();
		downloadLinks.remove(toRemove);
		entity.removeDownloadLink(toRemove);

		assertEquals(downloadLinks, entity.getDownloadLinks());
	}

	@Test
	public void testClearDownloadLink() throws Exception {
		entity.addDownloadLink(DOWNLOAD_LINK_FACTORY.getNew());
		entity.clearDownloadLinks();
		assertTrue(entity.getDownloadLinks().isEmpty());

	}
}
