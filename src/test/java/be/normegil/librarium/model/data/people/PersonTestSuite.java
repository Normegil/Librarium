package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.TestResult;
import be.normegil.librarium.tool.comparator.PropertyComparatorHelper;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

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

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Role> ROLE_FACTORY = FactoryRepository.get(Role.class);
	private static long index = 0L;

	@Override
	public Person getNew() {
		return getNew(true);
	}

	@Override
	public Person getNext() {
		return getNext(true);
	}

	@Override
	public Person getNew(boolean withLink) {
		Person.Builder builder = Person.builder()
				.from(RESPONSIBLE_FACTORY.getNew(withLink));

		if (withLink) {
			builder.addRole(ROLE_FACTORY.getNew(false));
		}

		return builder.build();

	}

	@Override
	public Person getNext(boolean withLink) {
		Person.Builder builder = Person.builder()
				.from(RESPONSIBLE_FACTORY.getNext(withLink));

		if (withLink) {
			builder.addRole(ROLE_FACTORY.getNext(false))
					.addRole(ROLE_FACTORY.getNext(false));
		}

		index += 1;
		return builder.build();
	}
}