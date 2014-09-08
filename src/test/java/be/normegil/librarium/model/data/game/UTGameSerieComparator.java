package be.normegil.librarium.model.data.game;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataComparableTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTGameSerieComparator extends AbstractDataComparableTest<GameSerie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<GameSerie> FACTORY = FactoryRepository.get(GameSerie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> GAME_FACTORY = FactoryRepository.get(Game.class);

	@Override
	protected GameSerie getNewEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected int compare(final GameSerie entity1, final GameSerie entity2) {
		return entity1.compareTo(entity2);
	}

	@Override
	public void testEquality() throws Exception {
		GameSerie entity = getEntity();
		GameSerie copy = new GameSerie(entity);
		assertEquals(compare(entity, copy), entity.compareTo(copy));
	}

	@Test
	public void testGame_First() throws Exception {
		GameSerie entity = getEntity();
		GameSerie copy = new GameSerie(entity);
		copy.addGame(GAME_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testGame_Second() throws Exception {
		GameSerie entity = getEntity();
		GameSerie copy = new GameSerie(entity);
		copy.addGame(GAME_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}
}
