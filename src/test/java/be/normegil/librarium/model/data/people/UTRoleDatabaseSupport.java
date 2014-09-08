package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractEntityDatabaseSupportTest;

public class UTRoleDatabaseSupport extends AbstractEntityDatabaseSupportTest<Role> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Role> FACTORY = FactoryRepository.get(Role.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Person> PERSON_FACTORY = FactoryRepository.get(Person.class);

	public UTRoleDatabaseSupport() {
		super(Role.class);
	}

	@Override
	protected Role initEntity() {
		return FACTORY.getNext(true);
	}

	@Override
	protected Role changeEntity(final Role entity) {
		entity.setActor(PERSON_FACTORY.getNext(true));
		return entity;
	}
}