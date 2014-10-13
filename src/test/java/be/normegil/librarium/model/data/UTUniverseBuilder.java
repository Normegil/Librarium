package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTUniverseBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Universe> FACTORY = FactoryRepository.get(Universe.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> MEDIA_FACTORY = FactoryRepository.get(Media.class);
	private static final String NAME = "Name";
	private static final String DESCRIPTION = "Description";
	private Universe.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = Universe.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFrom() throws Exception {
		Universe universe = FACTORY.getNew(true);
		Universe copy = entity.from(universe).build();
		assertEquals(universe, copy);
	}

	@Test
	public void testSetName() throws Exception {
		Universe universe = entity
				.setName(NAME)
				.build();
		assertEquals(NAME, universe.getName());
	}

	@Test
	public void testSetDescription() throws Exception {
		Universe universe = entity
				.setDescription(DESCRIPTION)
				.build();
		assertEquals(DESCRIPTION, universe.getDescription());
	}

	@Test
	public void testAddAllMedias() throws Exception {
		Collection<Media> toAdd = new HashSet<>();
		toAdd.add(MEDIA_FACTORY.getNew());
		toAdd.add(MEDIA_FACTORY.getNew());
		toAdd.add(MEDIA_FACTORY.getNew());

		Universe universe = entity
				.addAllMedias(toAdd)
				.build();

		assertTrue(universe.getMedias().containsAll(toAdd));
	}

	@Test
	public void testAddMedia() throws Exception {
		Media media = MEDIA_FACTORY.getNew();
		Universe universe = entity
				.addMedia(media)
				.build();

		assertTrue(universe.getMedias().contains(media));
	}
}