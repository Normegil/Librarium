package be.normegil.librarium.model.data;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataComparableTest;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class UTEntityComparator extends AbstractDataComparableTest<Entity> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Entity> FACTORY = FactoryRepository.get(Entity.class);

	@Override
	protected Entity getNewEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected int compare(final Entity entity1, final Entity entity2) {
		return entity1.compareTo(entity2);
	}

	@Test
	public void testEquality() throws Exception {
		Entity copy = new Entity();
		Entity entity = getEntity();
		new EntityHelper().setId(copy, entity.getId());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
	}

	@Test
	public void testId_First() throws Exception {
		Entity newEntity = FACTORY.getNext();
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(getEntity(), newEntity)));
	}

	@Test
	public void testId_Second() throws Exception {
		Entity newEntity = FACTORY.getNext();
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(newEntity, getEntity())));
	}
}
