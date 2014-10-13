package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractEntityDatabaseSupportTest;

public class UTPersonDatabaseSupport extends AbstractEntityDatabaseSupportTest<Person> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Person> FACTORY = FactoryRepository.get(Person.class);

	public UTPersonDatabaseSupport() {
		super(Person.class);
	}

	@Override
	protected Person initEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected Person changeEntity(final Person entity) {
		entity.setName(entity.getName() + 1);
		return entity;
	}
}