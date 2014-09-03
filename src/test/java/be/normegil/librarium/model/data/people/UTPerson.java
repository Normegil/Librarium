package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class UTPerson {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Person> FACTORY = FactoryRepository.get(Person.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Role> ROLE_FACTORY = FactoryRepository.get(Role.class);
	private Person entity;
	private Collection<Role> roles = new HashSet<>();

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getNew();
		roles.addAll(entity.getRoles());
	}

	@After
	public void tearDown() throws Exception {
		roles = null;
		entity = null;
	}

	@Test
	public void testCopyConstructor() throws Exception {
		Person copy = new Person(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testAddAllRoles() throws Exception {
		Collection<Role> toAdd = new HashSet<>();
		toAdd.add(ROLE_FACTORY.getNext());
		toAdd.add(ROLE_FACTORY.getNext());
		toAdd.add(ROLE_FACTORY.getNext());

		roles.addAll(toAdd);
		entity.addAllRoles(toAdd);
		assertEquals(roles, entity.getRoles());
	}

	@Test
	public void testAddRole() throws Exception {
		Role toAdd = ROLE_FACTORY.getNext();
		roles.add(toAdd);
		entity.addRole(toAdd);
		assertEquals(roles, entity.getRoles());
	}

	@Test
	public void testRemoveAllRoles() throws Exception {
		Role base = ROLE_FACTORY.getNext();
		Role second = ROLE_FACTORY.getNext();
		Role third = ROLE_FACTORY.getNext();

		Collection<Role> toAdd = new HashSet<>();
		toAdd.add(base);
		toAdd.add(second);
		toAdd.add(third);

		Collection<Role> toRemove = new HashSet<>();
		toRemove.add(second);
		toRemove.add(third);

		roles.addAll(toAdd);
		entity.addAllRoles(toAdd);

		roles.removeAll(toRemove);
		entity.removeAllRoles(toRemove);

		assertEquals(roles, entity.getRoles());
	}

	@Test
	public void testRemoveRole() throws Exception {
		Role toRemove = entity.getRoles().iterator().next();
		roles.remove(toRemove);
		entity.removeRole(toRemove);

		assertEquals(roles, entity.getRoles());
	}
}
