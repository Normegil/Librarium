package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.test.model.data.AbstractDataEqualityTest;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTDownloadLinkEquality extends AbstractDataEqualityTest<DownloadLink> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<DownloadLink> FACTORY = FactoryRepository.get(DownloadLink.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BaseMedia> BASE_MEDIA_FACTORY = FactoryRepository.get(BaseMedia.class);
	private static final String ALTERNATIVE_TITLE = "AlternativeTitle";
	private static final String ALTERNATIVE_DESCRIPTION = "AlternativeDescription";

	@Override
	protected DownloadLink getNewEntity() {
		return FACTORY.getNew();
	}

	@Test
	public void testUnchanged() throws Exception {
		DownloadLink entity = getEntity();
		DownloadLink copy = new DownloadLink(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentTitle() throws Exception {
		DownloadLink entity = getEntity();
		DownloadLink copy = new DownloadLink(entity);
		entity.setTitle(ALTERNATIVE_TITLE);
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentDescription() throws Exception {
		DownloadLink entity = getEntity();
		DownloadLink copy = new DownloadLink(entity);
		entity.setDescription(ALTERNATIVE_DESCRIPTION);
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentLink() throws Exception {
		DownloadLink entity = getEntity();
		DownloadLink copy = new DownloadLink(entity);
		entity.setLink(URL_FACTORY.getNext());
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentMedia() throws Exception {
		DownloadLink entity = getEntity();
		DownloadLink copy = new DownloadLink(entity);
		entity.setMedia(BASE_MEDIA_FACTORY.getNext());
		assertNotEquals(entity, copy);
	}
}