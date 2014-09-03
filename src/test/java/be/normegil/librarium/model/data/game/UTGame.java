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

public class UTGame {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> FACTORY = FactoryRepository.get(Game.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<GameSerie> GAME_SERIE_FACTORY = FactoryRepository.get(GameSerie.class);
	private Game entity;
	private Collection<Responsible> developers;
	private Collection<Responsible> editors;
	private Collection<Responsible> composers;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getNew();
		developers = entity.getDevelopers();
		editors = entity.getEditors();
		composers = entity.getComposers();
	}

	@After
	public void tearDown() throws Exception {
		composers = null;
		editors = null;
		developers = null;
		entity = null;
	}

	@Test
	public void testCopyConstructor() throws Exception {
		Game copy = new Game(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testAddAllDevelopers() throws Exception {
		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());

		developers.addAll(toAdd);
		entity.addAllDevelopers(toAdd);
		assertEquals(developers, entity.getDevelopers());
	}

	@Test
	public void testAddDeveloper() throws Exception {
		Responsible toAdd = RESPONSIBLE_FACTORY.getNext();
		developers.add(toAdd);
		entity.addDeveloper(toAdd);
		assertEquals(developers, entity.getDevelopers());
	}

	@Test
	public void testRemoveAllDevelopers() throws Exception {
		Responsible base = RESPONSIBLE_FACTORY.getNext();
		Responsible second = RESPONSIBLE_FACTORY.getNext();
		Responsible third = RESPONSIBLE_FACTORY.getNext();

		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(base);
		toAdd.add(second);
		toAdd.add(third);

		Collection<Responsible> toRemove = new HashSet<>();
		toRemove.add(second);
		toRemove.add(third);

		developers.addAll(toAdd);
		entity.addAllDevelopers(toAdd);

		developers.removeAll(toRemove);
		entity.removeAllDevelopers(toRemove);

		assertEquals(developers, entity.getDevelopers());
	}

	@Test
	public void testRemoveDeveloper() throws Exception {
		Responsible toRemove = entity.getDevelopers().iterator().next();
		developers.remove(toRemove);
		entity.removeDeveloper(toRemove);

		assertEquals(developers, entity.getDevelopers());
	}

	@Test
	public void testAddAllEditors() throws Exception {
		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());

		editors.addAll(toAdd);
		entity.addAllEditors(toAdd);
		assertEquals(editors, entity.getEditors());
	}

	@Test
	public void testAddEditor() throws Exception {
		Responsible toAdd = RESPONSIBLE_FACTORY.getNext();
		editors.add(toAdd);
		entity.addEditor(toAdd);
		assertEquals(editors, entity.getEditors());
	}

	@Test
	public void testRemoveAllEditors() throws Exception {
		Responsible base = RESPONSIBLE_FACTORY.getNext();
		Responsible second = RESPONSIBLE_FACTORY.getNext();
		Responsible third = RESPONSIBLE_FACTORY.getNext();

		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(base);
		toAdd.add(second);
		toAdd.add(third);

		Collection<Responsible> toRemove = new HashSet<>();
		toRemove.add(second);
		toRemove.add(third);

		editors.addAll(toAdd);
		entity.addAllEditors(toAdd);

		editors.removeAll(toRemove);
		entity.removeAllEditors(toRemove);

		assertEquals(editors, entity.getEditors());
	}

	@Test
	public void testRemoveEditor() throws Exception {
		Responsible toRemove = entity.getEditors().iterator().next();
		editors.remove(toRemove);
		entity.removeEditor(toRemove);

		assertEquals(editors, entity.getEditors());
	}

	@Test
	public void testAddAllComposers() throws Exception {
		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());

		composers.addAll(toAdd);
		entity.addAllComposers(toAdd);
		assertEquals(composers, entity.getComposers());
	}

	@Test
	public void testAddComposer() throws Exception {
		Responsible toAdd = RESPONSIBLE_FACTORY.getNext();
		composers.add(toAdd);
		entity.addComposer(toAdd);
		assertEquals(composers, entity.getComposers());
	}

	@Test
	public void testRemoveAllComposers() throws Exception {
		Responsible base = RESPONSIBLE_FACTORY.getNext();
		Responsible second = RESPONSIBLE_FACTORY.getNext();
		Responsible third = RESPONSIBLE_FACTORY.getNext();

		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(base);
		toAdd.add(second);
		toAdd.add(third);

		Collection<Responsible> toRemove = new HashSet<>();
		toRemove.add(second);
		toRemove.add(third);

		composers.addAll(toAdd);
		entity.addAllComposers(toAdd);

		composers.removeAll(toRemove);
		entity.removeAllComposers(toRemove);

		assertEquals(composers, entity.getComposers());
	}

	@Test
	public void testRemoveComposer() throws Exception {
		Responsible toRemove = entity.getComposers().iterator().next();
		composers.remove(toRemove);
		entity.removeComposer(toRemove);

		assertEquals(composers, entity.getComposers());
	}

	@Test
	public void testSetSerie() throws Exception {
		GameSerie gameSerie = GAME_SERIE_FACTORY.getNext();
		entity.setSerie(gameSerie);
		assertEquals(gameSerie, entity.getSerie());
	}
}
