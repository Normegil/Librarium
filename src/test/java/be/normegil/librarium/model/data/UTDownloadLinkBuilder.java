package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTDownloadLinkBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<DownloadLink> DOWNLOAD_LINK_FACTORY = FactoryRepository.get(DownloadLink.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BaseMedia> BASE_MEDIA_FACTORY = FactoryRepository.get(BaseMedia.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private static final String TITLE = "TITLE";
	private static final String ALTERNATIVE_DESCRIPTION = "AlternativeDescription";
	private static final String EMPTY_STRING = "";
	private DownloadLink.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = DownloadLink.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFrom() throws Exception {
		DownloadLink downloadLink = DOWNLOAD_LINK_FACTORY.getNext(true);
		DownloadLink copy = entity.from(downloadLink).build();
		assertEquals(downloadLink, copy);
	}

	@Test
	public void testSetTitle() throws Exception {
		DownloadLink downloadLink = entity
				.setTitle(TITLE)
				.build();
		assertEquals(TITLE, downloadLink.getTitle());
	}

	@Test
	public void testSetDescription() throws Exception {
		DownloadLink downloadLink = entity
				.setDescription(ALTERNATIVE_DESCRIPTION)
				.build();
		assertEquals(ALTERNATIVE_DESCRIPTION, downloadLink.getDescription());
	}

	@Test
	public void testSetLink() throws Exception {
		URL url = URL_FACTORY.getNext();
		DownloadLink downloadLink = entity
				.setLink(url)
				.build();
		assertEquals(url, downloadLink.getLink());
	}

	@Test
	public void testSetMedia() throws Exception {
		BaseMedia media = BASE_MEDIA_FACTORY.getNext();
		DownloadLink downloadLink = entity
				.setMedia(media)
				.build();
		assertEquals(media, downloadLink.getMedia());
	}
}