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

public class UTBaseMediaBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BaseMedia> FACTORY = FactoryRepository.get(BaseMedia.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<DownloadLink> DOWNLOAD_LINK_FACTORY = FactoryRepository.get(DownloadLink.class);
	private static final String TITLE = "Title";
	private static final String DESCRIPTION = "Description";
	private static final String TAG = "NewTag";
	private FakeBaseMedia.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = FakeBaseMedia.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFrom() throws Exception {
		BaseMedia baseMedia = FACTORY.getNext(true);
		BaseMedia copy = entity.from(baseMedia).build();
		assertEquals(baseMedia, copy);
	}

	@Test
	public void testSetTitle() throws Exception {
		BaseMedia baseMedia = entity
				.setTitle(TITLE)
				.build();
		assertEquals(TITLE, baseMedia.getTitle());
	}

	@Test
	public void testSetDescription() throws Exception {
		BaseMedia baseMedia = entity
				.setDescription(DESCRIPTION)
				.build();
		assertEquals(DESCRIPTION, baseMedia.getDescription());
	}

	@Test
	public void testAddTag() throws Exception {
		BaseMedia baseMedia = entity
				.addTag(TAG)
				.build();
		assertTrue(baseMedia.getTags().contains(TAG));
	}

	@Test
	public void testAddAllTags() throws Exception {
		Collection<String> toAdd = new HashSet<>();
		toAdd.add(TAG);
		toAdd.add(TAG + 1);

		BaseMedia baseMedia = entity
				.addAllTags(toAdd)
				.build();
		assertTrue(baseMedia.getTags().containsAll(toAdd));
	}

	@Test
	public void testSetOfficialWebsite() throws Exception {
		URL url = URL_FACTORY.getNext();
		BaseMedia baseMedia = entity
				.setOfficialWebsite(url)
				.build();
		assertEquals(url, baseMedia.getOfficialWebsite());
	}

	@Test
	public void testSetWikipediaPage() throws Exception {
		URL url = URL_FACTORY.getNext();
		BaseMedia baseMedia = entity
				.setWikipediaPage(url)
				.build();
		assertEquals(url, baseMedia.getWikipediaPage());
	}

	@Test
	public void testAddStore() throws Exception {
		URL url = URL_FACTORY.getNext();
		BaseMedia baseMedia = entity
				.addStore(url)
				.build();
		assertTrue(baseMedia.getStores().contains(url));
	}

	@Test
	public void testAddAllStores() throws Exception {
		Collection<URL> toAdd = new HashSet<>();
		toAdd.add(URL_FACTORY.getNext());
		toAdd.add(URL_FACTORY.getNext());

		BaseMedia baseMedia = entity
				.addAllStores(toAdd)
				.build();
		assertTrue(baseMedia.getStores().containsAll(toAdd));
	}

	@Test
	public void testAddDownloadLink() throws Exception {
		DownloadLink link = DOWNLOAD_LINK_FACTORY.getNext();
		BaseMedia baseMedia = entity
				.addDownloadLink(link)
				.build();
		assertTrue(baseMedia.getDownloadLinks().contains(link));
	}

	@Test
	public void testAddAllDownloadLinks() throws Exception {
		Collection<DownloadLink> toAdd = new HashSet<>();
		toAdd.add(DOWNLOAD_LINK_FACTORY.getNext());
		toAdd.add(DOWNLOAD_LINK_FACTORY.getNext());

		BaseMedia baseMedia = entity
				.addAllDownloadLinks(toAdd)
				.build();
		assertTrue(baseMedia.getDownloadLinks().containsAll(toAdd));
	}
}