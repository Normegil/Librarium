package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class UTSerie {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Serie> FACTORY = FactoryRepository.get(Serie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<SerieSeason> SERIE_SEASON_FACTORY = FactoryRepository.get(SerieSeason.class);
	private Serie entity;
	private Collection<SerieSeason> seasons;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getDefault();
		seasons = entity.getSeasons();
	}

	@After
	public void tearDown() throws Exception {
		seasons = null;
		entity = null;
	}

	@Test
	public void testCopyConstructor() throws Exception {
		Serie copy = new Serie(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testAddAllSeasons() throws Exception {
		Collection<SerieSeason> toAdd = new HashSet<>();
		toAdd.add(SERIE_SEASON_FACTORY.getNew());
		toAdd.add(SERIE_SEASON_FACTORY.getNew());
		toAdd.add(SERIE_SEASON_FACTORY.getNew());

		seasons.addAll(toAdd);
		entity.addAllSeasons(toAdd);
		assertEquals(seasons, entity.getSeasons());
	}

	@Test
	public void testAddSeason() throws Exception {
		SerieSeason toAdd = SERIE_SEASON_FACTORY.getNew();
		seasons.add(toAdd);
		entity.addSeason(toAdd);
		assertEquals(seasons, entity.getSeasons());
	}

	@Test
	public void testRemoveAllSeasons() throws Exception {
		SerieSeason base = SERIE_SEASON_FACTORY.getNew();
		SerieSeason second = SERIE_SEASON_FACTORY.getNew();
		SerieSeason third = SERIE_SEASON_FACTORY.getNew();

		Collection<SerieSeason> toAdd = new HashSet<>();
		toAdd.add(base);
		toAdd.add(second);
		toAdd.add(third);

		Collection<SerieSeason> toRemove = new HashSet<>();
		toRemove.add(second);
		toRemove.add(third);

		seasons.addAll(toAdd);
		entity.addAllSeasons(toAdd);

		seasons.removeAll(toRemove);
		entity.removeAllSeasons(toRemove);

		assertEquals(seasons, entity.getSeasons());
	}

	@Test
	public void testRemoveSeason() throws Exception {
		SerieSeason toRemove = entity.getSeasons().iterator().next();
		seasons.remove(toRemove);
		entity.removeSeason(toRemove);

		assertEquals(seasons, entity.getSeasons());
	}
}
