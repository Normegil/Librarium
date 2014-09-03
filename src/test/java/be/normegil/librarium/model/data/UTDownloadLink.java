package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTDownloadLink {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<DownloadLink> FACTORY = FactoryRepository.get(DownloadLink.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BaseMedia> BASE_MEDIA_FACTORY = FactoryRepository.get(BaseMedia.class);

	private static final String ALTERNATIVE_TITLE = "AlternativeTitle";
	private static final String ALTERNATIVE_DESCRIPTION = "AlternativeDescription";
	private DownloadLink entity;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getNew();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testCopyConstructor() throws Exception {
		DownloadLink copy = new DownloadLink(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testSetTitle() throws Exception {
		entity.setTitle(ALTERNATIVE_TITLE);
		assertEquals(ALTERNATIVE_TITLE, entity.getTitle());
	}

	@Test
	public void testSetDescription() throws Exception {
		entity.setDescription(ALTERNATIVE_DESCRIPTION);
		assertEquals(ALTERNATIVE_DESCRIPTION, entity.getDescription());
	}

	@Test
	public void testSetLink() throws Exception {
		URL alternativeLink = URL_FACTORY.getNext();
		entity.setLink(alternativeLink);
		assertEquals(alternativeLink, entity.getLink());
	}

	@Test
	public void testSetMedia() throws Exception {
		BaseMedia media = BASE_MEDIA_FACTORY.getNext();
		entity.setMedia(media);
		assertEquals(media, entity.getMedia());
	}

	@Test
	public void testToString() throws Exception {
		DownloadLink link = FACTORY.getNew();
		assertEquals("DownloadLink[id=<null>,title=Title,description=Description,link=URL[url=http://Host:42/File]]", link.toString());
	}
}
