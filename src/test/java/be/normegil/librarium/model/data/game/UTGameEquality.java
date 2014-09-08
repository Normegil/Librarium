package be.normegil.librarium.model.data.game;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataEqualityTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTGameEquality extends AbstractDataEqualityTest<Game> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> FACTORY = FactoryRepository.get(Game.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<GameSerie> GAME_SERIE_FACTORY = FactoryRepository.get(GameSerie.class);

	@Override
	protected Game getNewEntity() {
		return FACTORY.getNew();
	}

	@Test
	public void testUnchanged() throws Exception {
		Game entity = getEntity();
		Game copy = new Game(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentDeveloper() throws Exception {
		Game entity = getEntity();
		Game copy = new Game(entity);
		entity.addDeveloper(RESPONSIBLE_FACTORY.getNext());
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentEditor() throws Exception {
		Game entity = getEntity();
		Game copy = new Game(entity);
		entity.addEditor(RESPONSIBLE_FACTORY.getNext());
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentComposer() throws Exception {
		Game entity = getEntity();
		Game copy = new Game(entity);
		entity.addComposer(RESPONSIBLE_FACTORY.getNext());
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentSerie() throws Exception {
		Game entity = getEntity();
		Game copy = new Game(entity);
		entity.setSerie(GAME_SERIE_FACTORY.getNext());
		assertNotEquals(entity, copy);
	}
}