package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.dao.DatabaseDAO;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataEqualityTest;
import org.junit.Test;

import java.net.URI;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTBaseMediaDigestEquality extends AbstractDataEqualityTest<BaseMedia.BaseMediaDigest> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> BASE_MEDIA_FACTORY = FactoryRepository.get(Game.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<DownloadLink> DOWNLOAD_LINK_FACTORY = FactoryRepository.get(DownloadLink.class);

	@Override
	protected BaseMedia.BaseMediaDigest getNewEntity() {
		return new BaseMedia.BaseMediaDigest();
	}

	@Test
	public void testUnchanged() throws Exception {
		BaseMedia entity = getBaseMedia();
		URL url = URL_FACTORY.getNext();
		URI baseURI = url.toURI();
		BaseMedia.BaseMediaDigest digest1 = new BaseMedia.BaseMediaDigest();
		BaseMedia.BaseMediaDigest digest2 = new BaseMedia.BaseMediaDigest();
		digest1.fromBase(baseURI, entity);
		digest2.fromBase(baseURI, entity);
		assertEquals(digest1, digest2);
	}

	@Test
	public void testDifferentTitle() throws Exception {
		BaseMedia entity = getBaseMedia();
		URL url = URL_FACTORY.getNext();
		URI baseURI = url.toURI();
		BaseMedia.BaseMediaDigest digest1 = new BaseMedia.BaseMediaDigest();
		BaseMedia.BaseMediaDigest digest2 = new BaseMedia.BaseMediaDigest();
		digest1.fromBase(baseURI, entity);
		entity.setTitle(entity.getTitle() + 1);
		digest2.fromBase(baseURI, entity);
		assertNotEquals(digest1, digest2);
	}

	@Test
	public void testDifferentDescription() throws Exception {
		BaseMedia entity = getBaseMedia();
		URL url = URL_FACTORY.getNext();
		URI baseURI = url.toURI();
		BaseMedia.BaseMediaDigest digest1 = new BaseMedia.BaseMediaDigest();
		BaseMedia.BaseMediaDigest digest2 = new BaseMedia.BaseMediaDigest();
		digest1.fromBase(baseURI, entity);
		entity.setDescription(entity.getDescription() + 1);
		digest2.fromBase(baseURI, entity);
		assertNotEquals(digest1, digest2);
	}

	@Test
	public void testDifferentTags() throws Exception {
		BaseMedia entity = getBaseMedia();
		URL url = URL_FACTORY.getNext();
		URI baseURI = url.toURI();
		BaseMedia.BaseMediaDigest digest1 = new BaseMedia.BaseMediaDigest();
		BaseMedia.BaseMediaDigest digest2 = new BaseMedia.BaseMediaDigest();
		digest1.fromBase(baseURI, entity);
		entity.addTag(UUID.randomUUID().toString());
		digest2.fromBase(baseURI, entity);
		assertNotEquals(digest1, digest2);
	}

	@Test
	public void testDifferentOfficialWebsite() throws Exception {
		BaseMedia entity = getBaseMedia();
		URL url = URL_FACTORY.getNext();
		URI baseURI = url.toURI();
		BaseMedia.BaseMediaDigest digest1 = new BaseMedia.BaseMediaDigest();
		BaseMedia.BaseMediaDigest digest2 = new BaseMedia.BaseMediaDigest();
		digest1.fromBase(baseURI, entity);
		entity.setOfficialWebsite(URL_FACTORY.getNext());
		digest2.fromBase(baseURI, entity);
		assertNotEquals(digest1, digest2);
	}

	@Test
	public void testDifferentWikipediaPage() throws Exception {
		BaseMedia entity = getBaseMedia();
		URL url = URL_FACTORY.getNext();
		URI baseURI = url.toURI();
		BaseMedia.BaseMediaDigest digest1 = new BaseMedia.BaseMediaDigest();
		BaseMedia.BaseMediaDigest digest2 = new BaseMedia.BaseMediaDigest();
		digest1.fromBase(baseURI, entity);
		entity.setWikipediaPage(URL_FACTORY.getNext());
		digest2.fromBase(baseURI, entity);
		assertNotEquals(digest1, digest2);
	}

	@Test
	public void testDifferentStores() throws Exception {
		BaseMedia entity = getBaseMedia();
		URL url = URL_FACTORY.getNext();
		URI baseURI = url.toURI();
		BaseMedia.BaseMediaDigest digest1 = new BaseMedia.BaseMediaDigest();
		BaseMedia.BaseMediaDigest digest2 = new BaseMedia.BaseMediaDigest();
		digest1.fromBase(baseURI, entity);
		entity.addStore(URL_FACTORY.getNext());
		digest2.fromBase(baseURI, entity);
		assertNotEquals(digest1, digest2);
	}

	@Test
	public void testDifferentDownloadLink() throws Exception {
		BaseMedia entity = getBaseMedia();
		URL url = URL_FACTORY.getNext();
		URI baseURI = url.toURI();
		BaseMedia.BaseMediaDigest digest1 = new BaseMedia.BaseMediaDigest();
		BaseMedia.BaseMediaDigest digest2 = new BaseMedia.BaseMediaDigest();
		digest1.fromBase(baseURI, entity);
		DownloadLink downloadLink = DOWNLOAD_LINK_FACTORY.getNext();
		new EntityHelper().setId(downloadLink, UUID.randomUUID());
		entity.addDownloadLink(downloadLink);
		digest2.fromBase(baseURI, entity);
		assertNotEquals(digest1, digest2);
	}

	private BaseMedia getBaseMedia() {
		BaseMedia entity = BASE_MEDIA_FACTORY.getNext();
		EntityHelper entityHelper = new EntityHelper();
		for (DownloadLink downloadLink : entity.getDownloadLinks()) {
			entityHelper.setId(downloadLink, UUID.randomUUID());
		}
		return entity;
	}

	@Test
	public void testDifferentDownloadLinkDAO() throws Exception {
		BaseMedia.BaseMediaDigest digest1 = new BaseMedia.BaseMediaDigest();
		BaseMedia.BaseMediaDigest digest2 = new BaseMedia.BaseMediaDigest();
		digest1.setDownloadLinkDAO(new DatabaseDAO<>(DownloadLink.class));
		digest2.setDownloadLinkDAO(new DatabaseDAO<>(DownloadLink.class));
		assertEquals(digest1, digest2);
	}
}