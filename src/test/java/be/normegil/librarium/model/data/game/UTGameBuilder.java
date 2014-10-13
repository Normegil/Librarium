package be.normegil.librarium.model.data.game;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTGameBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> GAME_FACTORY = FactoryRepository.get(Game.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<GameSerie> GAME_SERIE_FACTORY = FactoryRepository.get(GameSerie.class);
	private Game.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = Game.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFrom() throws Exception {
		Game game = GAME_FACTORY.getNew();
		Game copy = entity.from(game).build();
		assertEquals(game, copy);
	}

	@Test
	public void testAddAllDevelopers() throws Exception {
		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(RESPONSIBLE_FACTORY.getNew());
		toAdd.add(RESPONSIBLE_FACTORY.getNew());
		toAdd.add(RESPONSIBLE_FACTORY.getNew());

		Game game = entity
				.addAllDevelopers(toAdd)
				.build();

		assertTrue(game.getDevelopers().containsAll(toAdd));
	}

	@Test
	public void testAddDeveloper() throws Exception {
		Responsible responsible = RESPONSIBLE_FACTORY.getNew();
		Game game = entity
				.addDeveloper(responsible)
				.build();

		assertTrue(game.getDevelopers().contains(responsible));
	}

	@Test
	public void testAddAllEditors() throws Exception {
		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(RESPONSIBLE_FACTORY.getNew());
		toAdd.add(RESPONSIBLE_FACTORY.getNew());
		toAdd.add(RESPONSIBLE_FACTORY.getNew());

		Game game = entity
				.addAllEditors(toAdd)
				.build();

		assertTrue(game.getEditors().containsAll(toAdd));
	}

	@Test
	public void testAddEditor() throws Exception {
		Responsible responsible = RESPONSIBLE_FACTORY.getNew();
		Game game = entity
				.addEditor(responsible)
				.build();

		assertTrue(game.getEditors().contains(responsible));
	}

	@Test
	public void testAddAllComposers() throws Exception {
		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(RESPONSIBLE_FACTORY.getNew());
		toAdd.add(RESPONSIBLE_FACTORY.getNew());
		toAdd.add(RESPONSIBLE_FACTORY.getNew());

		Game game = entity
				.addAllComposers(toAdd)
				.build();

		assertTrue(game.getComposers().containsAll(toAdd));
	}

	@Test
	public void testAddComposer() throws Exception {
		Responsible responsible = RESPONSIBLE_FACTORY.getNew();
		Game game = entity
				.addComposer(responsible)
				.build();

		assertTrue(game.getComposers().contains(responsible));
	}

	@Test
	public void testSetSerie() throws Exception {
		GameSerie serie = GAME_SERIE_FACTORY.getNew();
		Game game = entity
				.setSerie(serie)
				.build();
		assertEquals(serie, game.getSerie());
	}
}