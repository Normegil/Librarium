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
import static org.junit.Assert.assertTrue;

public class UTPersonBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Person> PERSON_FACTORY = FactoryRepository.get(Person.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Role> ROLE_FACTORY = FactoryRepository.get(Role.class);
	private Person.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = Person.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFrom() throws Exception {
		Person person = PERSON_FACTORY.getNew();
		Person copy = entity.from(person).build();
		assertEquals(person, copy);
	}

	@Test
	public void testAddAllRoles() throws Exception {
		Collection<Role> toAdd = new HashSet<>();
		toAdd.add(ROLE_FACTORY.getNew());
		toAdd.add(ROLE_FACTORY.getNew());
		toAdd.add(ROLE_FACTORY.getNew());

		Person person = entity
				.addAllRoles(toAdd)
				.build();

		assertTrue(person.getRoles().containsAll(toAdd));
	}

	@Test
	public void testAddRole() throws Exception {
		Role role = ROLE_FACTORY.getNew();
		Person person = entity
				.addRole(role)
				.build();

		assertTrue(person.getRoles().contains(role));
	}
}