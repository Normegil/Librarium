package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.AbstractDataEqualityTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTPersonEquality extends AbstractDataEqualityTest<Person> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Person> FACTORY = FactoryRepository.get(Person.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Role> ROLE_FACTORY = FactoryRepository.get(Role.class);

	@Override
	protected Person getNewEntity() {
		return FACTORY.getNew();
	}

	@Test
	public void testUnchanged() throws Exception {
		Person entity = getEntity();
		Person copy = new Person(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentRole() throws Exception {
		Person entity = getEntity();
		Person copy = new Person(entity);
		entity.addRole(ROLE_FACTORY.getNext());
		assertNotEquals(entity, copy);
	}
}