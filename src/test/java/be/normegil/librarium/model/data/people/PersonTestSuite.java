package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.UUID;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTPersonSafety.class,
		UTPerson.class,
		UTPersonEquality.class,
		UTPersonComparator.class,
		UTPersonBuilderSafety.class,
		UTPersonBuilder.class,
		UTPersonDatabaseSupport.class
})
public class PersonTestSuite implements DataFactory<Person> {

	public static final UUID DEFAULT_ID = UUID.fromString("");
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Role> ROLE_FACTORY = FactoryRepository.get(Role.class);
	private static long index = 0L;

	@Override
	public Person getDefault() {
		return getDefault(true, false);
	}

	@Override
	public Person getNew() {
		return getNew(true, false);
	}

	@Override
	public Person getDefault(final boolean withLink, final boolean withIds) {
		Person.Builder builder = Person.builder()
				.from(RESPONSIBLE_FACTORY.getDefault(withLink, withIds));

		if (withLink) {
			builder.addRole(ROLE_FACTORY.getDefault(false, withIds));
		}

		Person person = builder.build();
		if (withIds) {
			new EntityHelper().setId(person, DEFAULT_ID);
		}
		return person;

	}

	@Override
	public Person getNew(final boolean withLink, final boolean withIds) {
		Person.Builder builder = Person.builder()
				.from(RESPONSIBLE_FACTORY.getNew(withLink, withIds));

		if (withLink) {
			builder.addRole(ROLE_FACTORY.getNew(false, withIds))
					.addRole(ROLE_FACTORY.getNew(false, withIds));
		}

		index += 1;
		Person person = builder.build();
		if (withIds) {
			new EntityHelper().setId(person, DEFAULT_ID);
		}
		return person;
	}
}