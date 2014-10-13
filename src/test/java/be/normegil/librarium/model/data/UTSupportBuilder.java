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
import static org.junit.Assert.assertTrue;

public class UTSupportBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Support> FACTORY = FactoryRepository.get(Support.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> MEDIA_FACTORY = FactoryRepository.get(Media.class);
	private static final String NAME = "Name";
	private Support.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = Support.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFrom() throws Exception {
		Support support = FACTORY.getNew(true);
		Support copy = entity.from(support).build();
		assertEquals(support, copy);
	}

	@Test
	public void testSetName() throws Exception {
		Support support = entity
				.setName(NAME)
				.build();
		assertEquals(NAME, support.getName());
	}

	@Test
	public void testSetWikipediaPage() throws Exception {
		URL url = URL_FACTORY.getNew();
		Support support = entity
				.setWikipediaPage(url)
				.build();
		assertEquals(url, support.getWikipediaPage());
	}

	@Test
	public void testAddAllMedias() throws Exception {
		Collection<Media> toAdd = new HashSet<>();
		toAdd.add(MEDIA_FACTORY.getNew());
		toAdd.add(MEDIA_FACTORY.getNew());
		toAdd.add(MEDIA_FACTORY.getNew());

		Support support = entity
				.addAllMedias(toAdd)
				.build();

		assertTrue(support.getMedias().containsAll(toAdd));
	}

	@Test
	public void testAddMedia() throws Exception {
		Media media = MEDIA_FACTORY.getNew();
		Support support = entity
				.addMedia(media)
				.build();

		assertTrue(support.getMedias().contains(media));
	}
}