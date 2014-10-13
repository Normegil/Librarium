package be.normegil.librarium.model.data.people;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataComparableTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTPersonComparator extends AbstractDataComparableTest<Person> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Person> FACTORY = FactoryRepository.get(Person.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Role> ROLE_FACTORY = FactoryRepository.get(Role.class);

	@Override
	protected Person getNewEntity() {
		return FACTORY.getDefault();
	}

	@Override
	protected int compare(final Person entity1, final Person entity2) {
		return entity1.compareTo(entity2);
	}

	@Override
	public void testEquality() throws Exception {
		Person entity = getEntity();
		Person copy = new Person(entity);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
	}

	@Test
	public void testRole_First() throws Exception {
		Person entity = getEntity();
		Person copy = new Person(entity);
		copy.addRole(ROLE_FACTORY.getNew());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testRole_Second() throws Exception {
		Person entity = getEntity();
		Person copy = new Person(entity);
		copy.addRole(ROLE_FACTORY.getNew());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}
}
