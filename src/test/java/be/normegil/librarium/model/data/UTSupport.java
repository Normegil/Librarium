package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class UTSupport {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Support> FACTORY = FactoryRepository.get(Support.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> MEDIA_FACTORY = FactoryRepository.get(Media.class);
	private static final String NAME = "Name";
	private Support entity;
	private Collection<Media> medias;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getNext();
		medias = entity.getMedias();
	}

	@After
	public void tearDown() throws Exception {
		medias = null;
		entity = null;
	}

	@Test
	public void testCopyConstructor() throws Exception {
		Support copy = new Support(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testSetName() throws Exception {
		entity.setName(NAME);
		assertEquals("Name", entity.getName());
	}

	@Test
	public void testSetWikipediaPage() throws Exception {
		URL wikipediaPage = URL_FACTORY.getNext();
		entity.setWikipediaPage(wikipediaPage);
		assertEquals(wikipediaPage, entity.getWikipediaPage());
	}

	@Test
	public void testAddAllMedias() throws Exception {
		Collection<Media> toAdd = new HashSet<>();
		toAdd.add(MEDIA_FACTORY.getNext());
		toAdd.add(MEDIA_FACTORY.getNext());
		toAdd.add(MEDIA_FACTORY.getNext());

		medias.addAll(toAdd);
		entity.addAllMedias(toAdd);
		assertEquals(medias, entity.getMedias());
	}

	@Test
	public void testAddMedia() throws Exception {
		Media toAdd = MEDIA_FACTORY.getNext();
		medias.add(toAdd);
		entity.addMedia(toAdd);
		assertEquals(medias, entity.getMedias());
	}

	@Test
	public void testRemoveAllMedias() throws Exception {
		Media base = MEDIA_FACTORY.getNext();
		Media second = MEDIA_FACTORY.getNext();
		Media third = MEDIA_FACTORY.getNext();

		Collection<Media> toAdd = new HashSet<>();
		toAdd.add(base);
		toAdd.add(second);
		toAdd.add(third);

		Collection<Media> toRemove = new HashSet<>();
		toRemove.add(second);
		toRemove.add(third);

		medias.addAll(toAdd);
		entity.addAllMedias(toAdd);

		medias.removeAll(toRemove);
		entity.removeAllMedias(toRemove);

		assertEquals(medias, entity.getMedias());
	}

	@Test
	public void testRemoveMedia() throws Exception {
		Media toRemove = entity.getMedias().iterator().next();
		medias.remove(toRemove);
		entity.removeMedia(toRemove);

		assertEquals(medias, entity.getMedias());
	}
}
