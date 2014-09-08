package be.normegil.librarium.model.dao.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.people.Person;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.dao.AbstractDAOTest;

import static org.junit.Assert.assertEquals;

public class UTPersonDatabaseDAO extends AbstractDAOTest<PersonDatabaseDAO, Person> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Person> FACTORY = FactoryRepository.get(Person.class);
	private static final String ALTERNATIVE_TITLE = "AlternativeTitle";

	@Override
	protected PersonDatabaseDAO initDAO() {
		return new PersonDatabaseDAO();
	}

	@Override
	protected Object getEntityId(final Person entity) {
		return entity.getId();
	}

	@Override
	protected void changeEntity(final Person entity) {
		entity.setName(ALTERNATIVE_TITLE);
	}

	@Override
	protected void assertChangedPropertyEquals(final Person foundEntity) {
		assertEquals(ALTERNATIVE_TITLE, foundEntity.getName());
	}

	@Override
	protected Person getNewData() {
		return FACTORY.getNext();
	}
}
