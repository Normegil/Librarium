package be.normegil.librarium.model.data;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataComparableTest;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertTrue;

public class UTEntityComparator extends AbstractDataComparableTest<Entity> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Entity> FACTORY = FactoryRepository.get(Entity.class);
	private static final UUID ID = UUID.fromString("40d46c43-0700-4f38-8f4a-dcfa8186195e");
	private static final UUID ALTERNATIVE_ID = UUID.fromString("72e608ea-202c-44aa-ae42-699130d8367c");

	@Override
	protected Entity getNewEntity() {
		Entity entity = FACTORY.getNew();
		new EntityHelper().setId(entity, ID);
		return entity;
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
		Entity newEntity = new Entity();
		new EntityHelper().setId(newEntity, ALTERNATIVE_ID);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(getEntity(), newEntity)));
	}

	@Test
	public void testId_Second() throws Exception {
		Entity newEntity = new Entity();
		new EntityHelper().setId(newEntity, ALTERNATIVE_ID);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(newEntity, getEntity())));
	}
}
