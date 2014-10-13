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

public class UTSerieSeason {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<SerieSeason> FACTORY = FactoryRepository.get(SerieSeason.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Serie> SERIE_FACTORY = FactoryRepository.get(Serie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<EpisodeSerie> EPISODE_SERIE_FACTORY = FactoryRepository.get(EpisodeSerie.class);
	private SerieSeason entity;
	private Collection<EpisodeSerie> episodes;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getDefault();
		episodes = entity.getEpisodes();
	}

	@After
	public void tearDown() throws Exception {
		episodes = null;
		entity = null;
	}

	@Test
	public void testCopyConstructor() throws Exception {
		SerieSeason copy = new SerieSeason(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testSetSerie() throws Exception {
		Serie serie = SERIE_FACTORY.getNew();
		entity.setSerie(serie);
		assertEquals(serie, entity.getSerie());
	}

	@Test
	public void testSetSeasonNumber() throws Exception {
		long seasonNumber = entity.getSeasonNumber() + 1;
		entity.setSeasonNumber(seasonNumber);
		assertEquals(seasonNumber, (Object) entity.getSeasonNumber());
	}

	@Test
	public void testAddAllEpisodes() throws Exception {
		Collection<EpisodeSerie> toAdd = new HashSet<>();
		toAdd.add(EPISODE_SERIE_FACTORY.getNew());
		toAdd.add(EPISODE_SERIE_FACTORY.getNew());
		toAdd.add(EPISODE_SERIE_FACTORY.getNew());

		episodes.addAll(toAdd);
		entity.addAllEpisodes(toAdd);
		assertEquals(episodes, entity.getEpisodes());
	}

	@Test
	public void testAddEpisode() throws Exception {
		EpisodeSerie toAdd = EPISODE_SERIE_FACTORY.getNew();
		episodes.add(toAdd);
		entity.addEpisode(toAdd);
		assertEquals(episodes, entity.getEpisodes());
	}

	@Test
	public void testRemoveAllEpisodes() throws Exception {
		EpisodeSerie base = EPISODE_SERIE_FACTORY.getNew();
		EpisodeSerie second = EPISODE_SERIE_FACTORY.getNew();
		EpisodeSerie third = EPISODE_SERIE_FACTORY.getNew();

		Collection<EpisodeSerie> toAdd = new HashSet<>();
		toAdd.add(base);
		toAdd.add(second);
		toAdd.add(third);

		Collection<EpisodeSerie> toRemove = new HashSet<>();
		toRemove.add(second);
		toRemove.add(third);

		episodes.addAll(toAdd);
		entity.addAllEpisodes(toAdd);

		episodes.removeAll(toRemove);
		entity.removeAllEpisodes(toRemove);

		assertEquals(episodes, entity.getEpisodes());
	}

	@Test
	public void testRemoveEpisode() throws Exception {
		EpisodeSerie toRemove = entity.getEpisodes().iterator().next();
		episodes.remove(toRemove);
		entity.removeEpisode(toRemove);

		assertEquals(episodes, entity.getEpisodes());
	}
}
