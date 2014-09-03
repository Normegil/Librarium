package be.normegil.librarium.model.data.game;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.AbstractDataComparableTest;
import be.normegil.librarium.tool.test.AbstractDataComparatorTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTGameComparator extends AbstractDataComparableTest<Game> {

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

	@Override
	protected int compare(final Game entity1, final Game entity2) {
		return entity1.compareTo(entity2);
	}

	@Override
	public void testEquality() throws Exception {
		Game entity = getEntity();
		Game copy = new Game(entity);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
		assertEquals(compare(entity, copy), entity.compareTo(copy));
	}

	@Test
	public void testDeveloper_First() throws Exception {
		Game entity = getEntity();
		Game copy = new Game(entity);
		copy.addDeveloper(RESPONSIBLE_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testDeveloper_Second() throws Exception {
		Game entity = getEntity();
		Game copy = new Game(entity);
		copy.addDeveloper(RESPONSIBLE_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}

	@Test
	public void testEditor_First() throws Exception {
		Game entity = getEntity();
		Game copy = new Game(entity);
		copy.addEditor(RESPONSIBLE_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testEditor_Second() throws Exception {
		Game entity = getEntity();
		Game copy = new Game(entity);
		copy.addEditor(RESPONSIBLE_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}

	@Test
	public void testComposer_First() throws Exception {
		Game entity = getEntity();
		Game copy = new Game(entity);
		copy.addComposer(RESPONSIBLE_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testComposer_Second() throws Exception {
		Game entity = getEntity();
		Game copy = new Game(entity);
		copy.addComposer(RESPONSIBLE_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}

	@Test
	public void testSerie_First() throws Exception {
		Game entity = getEntity();
		Game copy = new Game(entity);
		copy.setSerie(GAME_SERIE_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testSerie_Second() throws Exception {
		Game entity = getEntity();
		Game copy = new Game(entity);
		copy.setSerie(GAME_SERIE_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}
}
